package com.example.emergencyalert.screens

import android.content.Context
import android.os.Build
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import com.example.emergencyalert.sensor.SensorViewModel
import com.example.emergencyalert.ui.theme.MainRound
import com.example.emergencyalert.util.SendSms
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun HomeScreen(
    navController: NavHostController,
    context: Context,
    sensorViewModel: SensorViewModel
) {
    val smsPermissionState =
        rememberPermissionState(permission = android.Manifest.permission.SEND_SMS);
    LaunchedEffect(smsPermissionState) {
        if (!smsPermissionState.status.isGranted) {
            smsPermissionState.launchPermissionRequest()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
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
                MainHomeScreen(context = context, sensorViewModel = sensorViewModel)
            }

        }
    }
}


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun MainHomeScreen(context: Context, sensorViewModel: SensorViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var showDialog by remember { mutableStateOf(false) }

        if (sensorViewModel.value1 >= 20 && !showDialog) {
            sensorViewModel.pauseSensor()
            showDialog = true
        }

        if (showDialog) {
            IsAccidentDialog(
                onConfirm = {
                    showDialog = false
                    sensorViewModel.startSensor()
                    SendSms(context = context).sendSms(sensorViewModel.value1)
                },
                onDismiss = {
                    showDialog = false
                    sensorViewModel.startSensor()
                }
            )
        }

        TopRoundPart(sensorViewModel = sensorViewModel)
        Spacer(modifier = Modifier.height(16.dp))
        FeaturesGrid()

    }
}


@Composable
fun IsAccidentDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    val isAccident = remember { mutableStateOf(false) }
    var timeLeft by remember { mutableStateOf(30) }

    Dialog(onDismissRequest = {}) {
        LaunchedEffect(key1 = timeLeft) {
            while (timeLeft > 0 && !isAccident.value) {
                delay(1000)
                timeLeft--
            }
            if (!isAccident.value) {
                onConfirm()
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.elevatedCardElevation(),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 40.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFF00FF00),
                                Color(0xFF00FF00)
                            )
                        )
                    ).padding(25.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Is there an accident?",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )

                Text(
                    text = "Time left: $timeLeft seconds",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                )

                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Button(onClick = {
                        isAccident.value = true
                        onConfirm()
                    }) {
                        Text("Yes")
                    }
                    Button(onClick = {
                        isAccident.value = false
                        onDismiss()
                    }) {
                        Text("No")
                    }
                }
            }
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
                    .background(color = MainRound, shape = CircleShape),
            ) {
                Column(
                    modifier = Modifier
                        .size(200.dp)
                        .background(Color.Green, shape = CircleShape),
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
