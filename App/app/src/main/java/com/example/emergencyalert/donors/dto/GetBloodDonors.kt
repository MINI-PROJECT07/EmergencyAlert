package com.example.emergencyalert.donors.dto

import kotlinx.serialization.Serializable

@Serializable
data class GetBloodDonors(
    val bloodDonors: List<BloodDonorInfo>
)