package com.example.emergencyalert.screens.home

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState


@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MultiplePermissionDemo() {
    val multiplePermission = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.SEND_SMS,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
        )
    )
    val backgroundLocationPermission =
        rememberPermissionState(permission = Manifest.permission.ACCESS_BACKGROUND_LOCATION)

    val context = LocalContext.current

    var showRationalDialog by remember { mutableStateOf(false) }
    var showBgPermissionRationalDialog by remember { mutableStateOf(false) }

    DisposableEffect(key1 = multiplePermission) {
        if (!multiplePermission.allPermissionsGranted) {
            if (multiplePermission.shouldShowRationale) {
                showRationalDialog = true
            } else {
                multiplePermission.launchMultiplePermissionRequest()
                backgroundLocationPermission.launchPermissionRequest()
            }
        }
        if (!backgroundLocationPermission.status.isGranted) {
            showBgPermissionRationalDialog = true
        }
        onDispose { /* Cleanup if needed */ }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        if (showBgPermissionRationalDialog) {
            AlertDialog(
                onDismissRequest = {
                    showBgPermissionRationalDialog = false
                },
                title = {
                    Text(
                        text = "Permission",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                },
                text = {
                    Column {
                        Text(
                            text = "We need background location permission. For your safety, " +
                                    "please grant the permission.Go to settings location" +
                                    " permission allow all time .",
                            fontSize = 16.sp
                        )
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showBgPermissionRationalDialog = false
                            val intent = Intent(
                                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                Uri.fromParts("package", context.packageName, null)
                            )
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            ContextCompat.startActivity(context, intent, null)
                        }
                    ) {
                        Text("OK", style = TextStyle(color = Color.Black))
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showBgPermissionRationalDialog = false
                        }
                    ) {
                        Text("Cancel", style = TextStyle(color = Color.Black))
                    }
                }
            )
        }
        if (showRationalDialog) {
            AlertDialog(
                onDismissRequest = {
                    showRationalDialog = false
                },
                title = {
                    Text(
                        text = "Permission",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                },
                text = {
                    Column {
                        multiplePermission.revokedPermissions.forEach { permission ->
                            when (permission.permission) {
                                Manifest.permission.SEND_SMS -> {
                                    Text(
                                        text = "We need SMS permission. Please grant the permission.",
                                        fontSize = 16.sp
                                    )
                                }

                                Manifest.permission.CALL_PHONE -> {
                                    Text(
                                        text = "We need Call permission. Please grant the permission.",
                                        fontSize = 16.sp
                                    )
                                }

                                Manifest.permission.ACCESS_FINE_LOCATION -> {
                                    Text(
                                        text = "We need Fine Location permission. Please grant the permission.",
                                        fontSize = 16.sp
                                    )
                                }

                                Manifest.permission.ACCESS_COARSE_LOCATION -> {
                                    Text(
                                        text = "We need Coarse Location permission. Please grant the permission.",
                                        fontSize = 16.sp
                                    )
                                }

                                Manifest.permission.CAMERA -> {
                                    Text(
                                        text = "We need Camera permission. Please grant the permission.",
                                        fontSize = 16.sp
                                    )
                                }

                                Manifest.permission.RECORD_AUDIO -> {
                                    Text(
                                        text = "We need Record Audio permission. Please grant the permission.",
                                        fontSize = 16.sp
                                    )
                                }
                                Manifest.permission.READ_PHONE_STATE -> {
                                    Text(
                                        text = "We need Read Phone State permission. Please grant the permission.",
                                        fontSize = 16.sp
                                    )
                                }
                            }
                        }
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showRationalDialog = false
                            val intent = Intent(
                                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                Uri.fromParts("package", context.packageName, null)
                            )
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            ContextCompat.startActivity(context, intent, null)
                        }
                    ) {
                        Text("OK", style = TextStyle(color = Color.Black))
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showRationalDialog = false
                        }
                    ) {
                        Text("Cancel", style = TextStyle(color = Color.Black))
                    }
                }
            )
        }
    }
}