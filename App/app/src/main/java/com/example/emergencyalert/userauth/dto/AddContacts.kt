package com.example.emergencyalert.userauth.dto

import kotlinx.serialization.Serializable

@Serializable
data class AddContacts(
    val emergencyNumbers: List<AddContact>
)

@Serializable
data class AddContact(
    val name: String,
    val phoneNo: String
)