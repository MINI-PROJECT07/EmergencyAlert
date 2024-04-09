package com.example.emergencyalert.accidents

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.emergencyalert.accidents.dto.CreateAccident
import com.example.emergencyalert.accidents.services.AccidentService
import com.example.emergencyalert.location.LatLong
import com.example.emergencyalert.userauth.dto.Contact
import com.example.emergencyalert.util.SendSms
import com.example.emergencyalert.util.generateLocationUrl
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class AccidentViewModel @Inject constructor(
    private val accidentService: AccidentService,
    private val authToken: String,
    private val sendSms: SendSms,
) : ViewModel() {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun accidentHappened(latLong: LatLong,contacts:List<Contact>?) {
        val accident = CreateAccident(
            latitude = latLong.latitude,
            longitude = latLong.longitude,
            location = generateLocationUrl(latLong),
            description = "I am in danger",
            time = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now())
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (contacts != null) {
                for(contact in contacts){
                    sendSms.sendSms(
                        latLong,
                        contact.phoneNo
                    )
                }
            }
        }
        val createAccidentResponse = accidentService.generateAccident(authToken, accident)
        println(createAccidentResponse)
    }
}