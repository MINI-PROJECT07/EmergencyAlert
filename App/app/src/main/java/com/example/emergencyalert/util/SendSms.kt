package com.example.emergencyalert.util

import android.content.Context
import android.location.Location
import android.os.Build
import android.telephony.SmsManager
import androidx.annotation.RequiresApi
import com.example.emergencyalert.location.LatLong

class SendSms(private val context: Context) {
    @RequiresApi(Build.VERSION_CODES.S)
    fun sendSms(latLong: LatLong, phoneNumber: String) {
        val smsManager =
            context.getSystemService<SmsManager>(SmsManager::class.java).createForSubscriptionId(1)
        smsManager.sendTextMessage(
            phoneNumber,
            null,
            "I am in danger. " +
                    "My location : ${generateLocationUrl(latLong)} ",
            null,
            null
        )
    }
}