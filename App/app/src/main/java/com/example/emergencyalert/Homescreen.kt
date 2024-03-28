package com.example.emergencyalert

import android.content.Context
import android.os.Build
import android.telephony.SmsManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.emergencyalert.sensor.SensorViewModel
import com.example.emergencyalert.ui.theme.Greenish
import com.example.emergencyalert.ui.theme.MainRound
import com.example.emergencyalert.util.SendSms


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun HomeScreen(navController: NavHostController, context: Context, viewModel: SensorViewModel) {
    val permissionResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = {
            println(it)
        }
    )
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .padding(20.dp)
                    .background(Color.White),
                containerColor = Color(0xFFF5F5F5),
                tonalElevation = 10.dp,

                ) {
                Row(modifier = Modifier.fillMaxSize()) {
                    Text("BottomAppBar")
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            permissionResultLauncher.launch(
                arrayOf(
                    android.Manifest.permission.SEND_SMS,
                    android.Manifest.permission.CALL_PHONE,
                    android.Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.READ_PHONE_STATE,
                )
            )
            val value = viewModel.value1
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                MainHomeScreen(context = context, viewModel = viewModel)
            }

        }
    }
}

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun MainHomeScreen(context: Context, viewModel: SensorViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopRoundPart(viewModel = viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        FeaturesGrid()
    }
}


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun TopRoundPart(
    modifier: Modifier = Modifier,
    viewModel: SensorViewModel
) {
    ElevatedCard(
        modifier = modifier.fillMaxWidth().padding(5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .background(color = MainRound, shape = CircleShape),
            ) {
                Column(
                    modifier = Modifier
                        .size(200.dp)
                        .background(Color.Green, shape = CircleShape),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "${viewModel.value1}", style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    ))
                }
            }
        }

    }
}

@Composable
fun FeaturesGrid(
    modifier: Modifier = Modifier,
    features: List<String> = listOf("Feature 1", "Feature 2", "Feature 3", "Feature 4")
) {
    Column(
        modifier = modifier
    ) {
        val chunks = features.chunked(2)
        chunks.forEach { chunk ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                chunk.forEach { feature ->
                    FeatureCard(text = feature, modifier = Modifier.weight(1f))
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}

@Composable
fun FeatureCard(
    modifier: Modifier = Modifier,
    text: String
) {
    ElevatedCard(
        modifier = modifier
            .padding(horizontal = 4.dp).height(130.dp),
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(16.dp)
        )
    }
}

//@Preview
//@Composable
//fun PreviewMainHomeScreen() {
//    MainHomeScreen()
//}
