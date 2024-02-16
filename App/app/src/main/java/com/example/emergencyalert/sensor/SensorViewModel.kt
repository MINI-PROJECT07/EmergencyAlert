package com.example.emergencyalert.sensor

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SensorViewModel @Inject constructor(
    private val accelerometerSensor: ParentSensor
):ViewModel(){

    var value1 by mutableStateOf(0);
    init {
        accelerometerSensor.startListening()
        accelerometerSensor.setOnSensorValuesChangedListener { values->
            Log.d("SensorViewModel", "onSensorValuesChanged: ${values}")
            value1 = values[0].toInt()
        }
    }
}