package com.example.emergencyalert.accidents.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Accident(
    @SerialName("_id") val id: String,
    val user: String,
    val location: String,
    val latitude: Latitude,
    val longitude: Longitude,
    val time: String, // Assuming time is represented as a String
    val description: String,
    val isTaken: Boolean,
    val takenBy: String?,
    val isResolved: Boolean,
    val resolvedBy: String?,
    val resolvedTime: String?, // Assuming resolvedTime is represented as a String
    val resolvedDescription: String?
)

@Serializable
data class Longitude(@SerialName("\$numberDecimal") val value: String)

@Serializable
data class Latitude(@SerialName("\$numberDecimal") val value: String)
