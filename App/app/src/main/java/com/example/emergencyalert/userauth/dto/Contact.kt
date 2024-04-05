package com.example.emergencyalert.userauth.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Contact(
    @SerialName("_id") val id: String,
    val name: String,
    val phoneNo: String
)