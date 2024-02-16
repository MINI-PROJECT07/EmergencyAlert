package com.example.emergencyalert.userauth.dto
import kotlinx.serialization.*

@Serializable
data class AuthToken(
    val success : Boolean,
    val authToken: String
)
