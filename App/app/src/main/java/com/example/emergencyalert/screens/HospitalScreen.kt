package com.example.emergencyalert.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.emergencyalert.dataStore
import com.example.emergencyalert.hospitals.services.HospitalService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Composable
fun HospitalScreen(context:Context) {
    val hospitalService = HospitalService.create()
    var authToken by remember {
        mutableStateOf("")
    }
    LaunchedEffect(key1 = true) {
        val authCounter = stringPreferencesKey("auth_counter")
        val authCounterFlow: Flow<String> = context.dataStore.data.map { preferences ->
            preferences[authCounter] ?: "0"
        }
        authCounterFlow.collect {
            if(it!="0"){
                authToken = it
            }
        }
        println("Auth Nearby Help")
        println("Auth 1 : $authToken")
        val hospitals =
            hospitalService.getHospitals(authToken)
        for(hospital in hospitals){
            println("Auth $hospital")
        }
    }
    Scaffold() {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)){

        }
    }
}