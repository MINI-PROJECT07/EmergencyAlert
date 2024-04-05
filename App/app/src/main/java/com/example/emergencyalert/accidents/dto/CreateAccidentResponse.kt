package com.example.emergencyalert.accidents.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateAccidentResponse(
    val success: Boolean,
    val message: String,
    val error: String
)