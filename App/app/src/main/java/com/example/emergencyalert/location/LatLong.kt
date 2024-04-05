package com.example.emergencyalert.location

import kotlinx.serialization.Serializable

@Serializable
data class LatLong(
    val latitude: Double?,
    val longitude: Double?
)