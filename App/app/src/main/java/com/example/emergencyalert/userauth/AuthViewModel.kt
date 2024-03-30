package com.example.emergencyalert.userauth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(): ViewModel(){
    private var authToken by mutableStateOf("")

    fun giveAuthToken(token: String){
        authToken = token
    }

    fun takeAuthToken():String{
        return authToken
    }
}