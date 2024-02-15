package com.example.emergencyalert.userauth.dto

data class UserRegistration(
    val name: String,
    val phone: String,
    val email: String,
    val password: String,
)