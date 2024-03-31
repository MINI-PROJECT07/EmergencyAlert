package com.example.emergencyalert.hospitals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emergencyalert.hospitals.dto.Hospital
import com.example.emergencyalert.hospitals.services.HospitalService
import com.example.emergencyalert.userauth.dto.AuthToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HospitalViewModel @Inject constructor(
    private val hospitalService: HospitalService,
    private val authToken: String
): ViewModel(){
    var hospitals = mutableListOf<Hospital>();

    fun getHospitals(){
        viewModelScope.launch {
            hospitals = hospitalService.getHospitals(authToken).toMutableList();
        }
    }
    init {
        getHospitals();
    }
}