package com.example.emergencyalert.userauth.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserRegistration(
    val name: String,
    val phoneNo: String,
    val email: String,
    val password: String,
)