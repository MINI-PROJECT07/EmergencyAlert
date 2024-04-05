package com.example.emergencyalert.userauth.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val name: String,
    val email: String,
    val phoneNo: String,
    val bloodGroup: String,
    val age: Int,
    val gender: String,
    val disease: List<String>,
    val otherInfo: String,
    val emergencyNumbers: List<Contact>,
    val date: String
)