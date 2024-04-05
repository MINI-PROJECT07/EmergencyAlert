package com.example.emergencyalert.userauth.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserMedInfo(
    val bloodGroup: String,
    val age: Int,
    val gender: String,
    val disease:List<String>,
    val otherInfo: String
)