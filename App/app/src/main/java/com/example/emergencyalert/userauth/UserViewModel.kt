package com.example.emergencyalert.userauth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emergencyalert.userauth.dto.UserInfo
import com.example.emergencyalert.userauth.services.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val authToken:String,
    private val authService: AuthService
):ViewModel(){
    var userInfo = mutableStateOf<UserInfo?>(null)

    suspend fun getUserInfo(){
        userInfo.value = authService.getUserInfo(authToken)
    }

    init {
        viewModelScope.launch {
            getUserInfo()
            println(userInfo.value)
        }
    }
}