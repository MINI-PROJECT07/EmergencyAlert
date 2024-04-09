package com.example.emergencyalert.screens.home

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.emergencyalert.accidents.AccidentViewModel
import com.example.emergencyalert.location.LatLong
import com.example.emergencyalert.location.LocationViewModel
import com.example.emergencyalert.sensor.SensorViewModel
import com.example.emergencyalert.userauth.UserViewModel
import com.example.emergencyalert.util.SendSms
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun AccidentDialog(
    sensorViewModel: SensorViewModel, context: Context,
    locationViewModel: LocationViewModel,
    accidentViewModel: AccidentViewModel,
    userViewModel: UserViewModel
) {
    val openDialog = remember { mutableStateOf(false) }
    val timeLeft = remember { mutableIntStateOf(30) }
    val isAccident = remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope();

    if (sensorViewModel.value1 >= 20 && !openDialog.value) {
        sensorViewModel.pauseSensor()
        openDialog.value = true
        sensorViewModel.value1 = 0
    }

    if (openDialog.value) {
        LaunchedEffect(Unit) {
            while (timeLeft.value > 0) {
                delay(1000)
                timeLeft.value -= 1
            }
            if (!isAccident.value) { // Automatically close dialog after 30 seconds
                val latLong = LatLong(
                    locationViewModel.location.value?.latitude,
                    locationViewModel.location.value?.longitude
                )
                scope.launch {
                    accidentViewModel.accidentHappened(
                        latLong,userViewModel.userInfo.value?.emergencyNumbers
                    );
                }
                sensorViewModel.startSensor()
                timeLeft.intValue = 30
                openDialog.value = false
            }
        }

        Dialog(
            onDismissRequest = {
                openDialog.value = false
                sensorViewModel.startSensor()
            }
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.elevatedCardElevation(),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
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

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "${timeLeft.value}",
                        style = TextStyle(
                            fontSize = 18.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Button(
                            onClick = {
                                openDialog.value = false
                                val latLong = LatLong(
                                    locationViewModel.location.value?.latitude,
                                    locationViewModel.location.value?.longitude
                                )
                                scope.launch {
                                    accidentViewModel.accidentHappened(
                                        latLong,userViewModel.userInfo.value?.emergencyNumbers
                                    );
                                }
                                isAccident.value = true
                                timeLeft.value = 30
                                sensorViewModel.startSensor()
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.Red)
                                .padding(8.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                        ) {
                            Text(
                                "Yes",
                                color = Color.White,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Button(
                            onClick = {
                                openDialog.value = false
                                isAccident.value = false
                                sensorViewModel.startSensor()
                                timeLeft.value = 30
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.Green)
                                .padding(8.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
                        ) {
                            Text(
                                "No",
                                color = Color.White,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}
