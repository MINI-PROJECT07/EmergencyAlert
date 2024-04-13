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
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAlert
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.core.content.ContextCompat.startActivity
import com.example.emergencyalert.accidents.AccidentViewModel
import com.example.emergencyalert.location.LatLong
import com.example.emergencyalert.routes.Screens
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
                .scrollable(
                    state = rememberScrollableState {
                        it
                    },
                    orientation = Orientation.Vertical,
                    enabled = true
                )
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
                    userViewModel = userViewModel,
                    navController = navController
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
    userViewModel: UserViewModel,
    navController: NavHostController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            Spacer(modifier = Modifier.height(10.dp))
            TopRoundPart(sensorViewModel = sensorViewModel)
            Spacer(modifier = Modifier.height(16.dp))
            GenerateAlertCard(sensorViewModel = sensorViewModel)
            Spacer(modifier = Modifier.height(20.dp))
            EditAndPauseButtons(navController = navController, sensorViewModel = sensorViewModel)
            AccidentDialog(
                sensorViewModel = sensorViewModel, context = context,
                locationViewModel = locationViewModel,
                accidentViewModel = accidentViewModel,
                userViewModel = userViewModel
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
            .padding(5.dp),
        colors = CardColors(
            containerColor = MainColor,
            contentColor = CardDefaults.cardColors().contentColor,
            disabledContentColor = CardDefaults.cardColors().disabledContentColor,
            disabledContainerColor = CardDefaults.cardColors().disabledContainerColor
        )

    ) {
        Text(
            text = "Current Acceleration", style = TextStyle(
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(color = MainColor, shape = CircleShape)
                    .border(
                        1.dp, Color.White, CircleShape
                    ),
            ) {
                Column(
                    modifier = Modifier
                        .size(120.dp),
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

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun GenerateAlertCard(
    modifier: Modifier = Modifier,
    sensorViewModel: SensorViewModel,

    ) {
    ElevatedCard(
        modifier = modifier
            .padding(horizontal = 4.dp),
        colors = CardColors(
            containerColor = Color(237, 8, 0),
            contentColor = CardDefaults.cardColors().contentColor,
            disabledContentColor = CardDefaults.cardColors().disabledContentColor,
            disabledContainerColor = CardDefaults.cardColors().disabledContainerColor
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Generate an alert",
                style = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            )
            OutlinedIconButton(
                onClick = {
                    sensorViewModel.pauseSensor()
                    sensorViewModel.value1 = 30
                },
                Modifier
                    .padding(15.dp)
                    .size(70.dp),
                border = BorderStroke(
                    1.dp, Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.AddAlert,
                    contentDescription = null,
                    Modifier
                        .size(50.dp)
                        .padding(10.dp),
                    tint = Color.White
                )
            }
        }
    }
}
@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun EditAndPauseButtons(
    navController: NavHostController,
    sensorViewModel: SensorViewModel
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        EditContactsButton(navController = navController)
        Spacer(modifier = Modifier.width(16.dp))
    }
}

@Composable
fun EditContactsButton(
    navController: NavHostController
) {
    Button(
        onClick = {
            navController.navigate(Screens.EditContacts.route){
                popUpTo(Screens.Home.route) {
                    inclusive = false
                }
                launchSingleTop = true
                restoreState = true
            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = MainColor,
            contentColor = Color.White
        ),
        modifier = Modifier.height(120.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Filled.Edit, contentDescription = null, modifier = Modifier.size(40.dp))
            Spacer(modifier = Modifier.height(5.dp))
            Text("Edit Contacts")
        }
    }
}

