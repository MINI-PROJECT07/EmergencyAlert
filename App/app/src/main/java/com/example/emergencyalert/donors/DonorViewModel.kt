package com.example.emergencyalert.donors

import androidx.lifecycle.ViewModel
import com.example.emergencyalert.donors.dto.BloodDonorInfo
import com.example.emergencyalert.donors.dto.CreateBloodDonor
import com.example.emergencyalert.donors.services.BloodDonorService
import com.example.emergencyalert.location.LatLong
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DonorViewModel @Inject constructor(
    private val bloodDonorService: BloodDonorService,
    private val authToken: String
) : ViewModel() {
    var bloodDonors = mutableListOf<BloodDonorInfo>();
    suspend fun createBloodDonor(
        createBloodDonor: CreateBloodDonor
    ) {
        bloodDonorService.createBloodDonor(authToken, createBloodDonor)
    }

    suspend fun getBloodDonorsNearest(
        latitude: Double,
        longitude: Double
    ) {
        println(latitude)
        println(longitude)
        bloodDonors =
            bloodDonorService.getBloodDonorsNearest(LatLong(latitude, longitude)).toMutableList()
    }
}