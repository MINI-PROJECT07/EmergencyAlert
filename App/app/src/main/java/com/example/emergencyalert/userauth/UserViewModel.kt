package com.example.emergencyalert.userauth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emergencyalert.userauth.dto.AddContacts
import com.example.emergencyalert.userauth.dto.UserInfo
import com.example.emergencyalert.userauth.dto.UserMedInfo
import com.example.emergencyalert.userauth.services.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val authToken: String, private val authService: AuthService
) : ViewModel() {
    var userInfo = mutableStateOf<UserInfo?>(null)
    var isMedInfoAdded = mutableStateOf(true)
    var areContactsAdded = mutableStateOf(true)

    suspend fun getUserInfo() {
        if (authToken == "0") {
            return
        }
        userInfo.value = authService.getUserInfo(authToken)
        checkMedInfoAdded()
        checkContactsAdded()
    }

    suspend fun addMedInfo(userMedInfo: UserMedInfo) {
        val res = authService.addMedInfo(userMedInfo, authToken)
        if (res) {
            isMedInfoAdded.value = true
            getUserInfo()

        }
    }

    suspend fun addContacts(addContacts: AddContacts) {
        val res = authService.addContacts(addContacts, authToken)
        if (res) {
            areContactsAdded.value = true
            getUserInfo()
        }
    }
    private fun checkMedInfoAdded() {
        if (userInfo.value != null) {
            if (userInfo.value!!.bloodGroup == "" ||
                userInfo.value!!.age == 0 || userInfo.value!!.gender == ""
            ) {
                isMedInfoAdded.value = false
            }
        }
    }

    private fun checkContactsAdded() {
        if (userInfo.value != null) {
            if (userInfo.value!!.emergencyNumbers.isEmpty()) {
                areContactsAdded.value = false
            }
        }
    }

    init {
        viewModelScope.launch {
            getUserInfo()
            println(userInfo.value)
        }
        checkMedInfoAdded()
        checkContactsAdded()
    }
}