package com.example.emergencyalert.donors.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateBloodDonor(
    val name: String,
    val bloodGroup: String,
    val phoneNo: String,
    val age: Int,
    val address: String,
    val permanantLocation: PermanantLocation
)

@Serializable
data class PermanantLocation(
    val latitude: Double,
    val longitude: Double
)