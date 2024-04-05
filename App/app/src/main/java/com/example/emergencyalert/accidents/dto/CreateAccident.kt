package com.example.emergencyalert.accidents.dto

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@Serializable
data class CreateAccident(
    val location: String,
    val latitude: Double?,
    val longitude: Double?,
    val time: String = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()),
    val description: String
)

