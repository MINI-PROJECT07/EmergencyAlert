package com.example.emergencyalert.screens.home

import com.example.emergencyalert.location.LocationViewModel
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.emergencyalert.sensor.SensorViewModel
import com.example.emergencyalert.ui.theme.MainColor
import com.example.emergencyalert.util.SendSms
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.delay
import android.Manifest.permission.ACCESS_BACKGROUND_LOCATION
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CALL_PHONE
import android.Manifest.permission.CAMERA
import android.Manifest.permission.RECORD_AUDIO
import android.Manifest.permission.SEND_SMS
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.core.content.ContextCompat.startActivity
import com.example.emergencyalert.accidents.AccidentViewModel
import com.example.emergencyalert.location.LatLong
import com.example.emergencyalert.userauth.UserViewModel

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun HomeScreen(
    navController: NavHostController,
    context: Context,
    sensorViewModel: SensorViewModel,
    locationViewModel: LocationViewModel,
    accidentViewModel: AccidentViewModel,
    userViewModel: UserViewModel
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { it ->
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                MultiplePermissionDemo()
                MainHomeScreen(
                    context = context,
                    sensorViewModel = sensorViewModel,
                    locationViewModel = locationViewModel,
                    accidentViewModel = accidentViewModel,
                    userViewModel = userViewModel
                )
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun MainHomeScreen(
    context: Context,
    sensorViewModel: SensorViewModel,
    locationViewModel: LocationViewModel,
    accidentViewModel: AccidentViewModel,
    userViewModel: UserViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            TopRoundPart(sensorViewModel = sensorViewModel)
            Spacer(modifier = Modifier.height(16.dp))
            FeaturesGrid()
            AccidentDialog(
                sensorViewModel = sensorViewModel, context = context,
                locationViewModel = locationViewModel,
                accidentViewModel = accidentViewModel,
                userViewModel= userViewModel
            )
        }

    }
}


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun TopRoundPart(
    modifier: Modifier = Modifier,
    sensorViewModel: SensorViewModel
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
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
                    .background(color = MainColor, shape = CircleShape),
            ) {
                Column(
                    modifier = Modifier
                        .size(200.dp)
                        .background(MainColor, shape = CircleShape),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${sensorViewModel.value1}", style = TextStyle(
                            fontSize = 30.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    )
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
            .padding(horizontal = 4.dp)
            .height(130.dp),
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(16.dp)
        )
    }
}
