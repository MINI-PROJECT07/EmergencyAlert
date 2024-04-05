package com.example.emergencyalert.hospitals.dto

import android.health.connect.datatypes.DistanceRecord
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Hospital (
    @SerialName("_id") val id: String,
    val name: String,
    val email: String,
    @SerialName("phoneNo") val phoneNo: String,
    val address: String,
    val city: String,
    val state: String,
    val pincode: String,
    val latitude: Latitude,
    val longitude: Longitude,
    val info: String,
    val date: String,
    val capacity: Int,
    val isActive: Boolean,
    val remCapacity: Int,
    val distance:Double?
)

@Serializable
data class Longitude(@SerialName("\$numberDecimal") val value: String)

@Serializable
data class Latitude(@SerialName("\$numberDecimal") val value: String)
