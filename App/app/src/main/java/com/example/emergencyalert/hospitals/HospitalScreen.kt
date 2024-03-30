package com.example.emergencyalert.hospitals

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.emergencyalert.hospitals.services.HospitalService
import com.example.emergencyalert.userauth.AuthViewModel

@Composable
fun HospitalScreen(context:Context) {
    val authViewModel = viewModel<AuthViewModel>()
    val authToken = authViewModel.takeAuthToken()
    val hospitalService = HospitalService.create()
    LaunchedEffect(key1 = true) {
        val hospitals =
            hospitalService.getHospitals("")
        for(hospital in hospitals){
            println(hospital)
        }
    }
    Scaffold() {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)){

        }
    }
}