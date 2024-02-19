package com.example.emergencyalert.util

import android.content.Context
import android.os.Build
import android.telephony.SmsManager
import androidx.annotation.RequiresApi

class SendSms(private val context: Context) {
    @RequiresApi(Build.VERSION_CODES.S)
    fun sendSms(acc: Int) {
        val smsManager =
            context.getSystemService<SmsManager>(SmsManager::class.java).createForSubscriptionId(1)
        smsManager.sendTextMessage(
            "+918767206376",
            null,
            "I am in danger, my current acceleration is $acc m/s^2",
            null,
            null
        )
    }
}