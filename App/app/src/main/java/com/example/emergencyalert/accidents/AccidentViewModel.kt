package com.example.emergencyalert.accidents

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.emergencyalert.accidents.dto.CreateAccident
import com.example.emergencyalert.accidents.services.AccidentService
import com.example.emergencyalert.location.LatLong
import com.example.emergencyalert.util.generateLocationUrl
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class AccidentViewModel @Inject constructor(
    private val accidentService: AccidentService,
    private val authToken: String
) : ViewModel() {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun accidentHappened(latLong: LatLong, acc: Int) {
        val accident = CreateAccident(
            latitude = latLong.latitude,
            longitude = latLong.longitude,
            location = generateLocationUrl(latLong),
            description = "Acceleration is ${acc}",
            time = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now())
        )
        val createAccidentResponse = accidentService.generateAccident(authToken, accident)
        println(createAccidentResponse)
    }
}