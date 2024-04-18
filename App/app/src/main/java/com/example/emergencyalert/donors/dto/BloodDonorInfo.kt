package com.example.emergencyalert.donors.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class BloodDonorInfo(
    val permanantLocation: PermanantLocationG,
    val user: String,
    val name: String,
    val phoneNo: String,
    val bloodGroup: String,
    val age: Int,
    val address: String
)

@Serializable
data class PermanantLocationG (
    val latitude: Latitude,
    val longitude: Longitude
)

@Serializable
data class Latitude(
    @SerialName("\$numberDecimal")
    val value: String
)

@Serializable
data class Longitude(
    @SerialName("\$numberDecimal")
    val value: String
)
