package com.example.emergencyalert.location

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import android.location.Location
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(private val defaultLocationClient: DefaultLocationClient) :
    ViewModel() {

    private var locationFlow: Flow<Location>? = null
    var location = mutableStateOf<Location?>(null);

    fun startLocationUpdates(interval: Long) {
        locationFlow = defaultLocationClient.getLocationUpdates(interval)
    }

    fun stopLocationUpdates() {
        locationFlow = null
    }

    init {
        startLocationUpdates(5000)
        viewModelScope.launch {
            locationFlow?.let {
                locationFlow?.collect {
                    location.value = it
                }
            }
        }
    }
}
