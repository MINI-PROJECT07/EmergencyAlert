package com.example.emergencyalert

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.emergencyalert.hospitals.services.HospitalService
import com.example.emergencyalert.userauth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_info")

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isLogged by remember { mutableStateOf(true) }
            val navController = rememberNavController()
            val authViewModel = viewModel<AuthViewModel>()
            LaunchedEffect(true) {
                val authCounter = stringPreferencesKey("auth_counter")
                val authCounterFlow: Flow<String> = dataStore.data.map { preferences ->
                    preferences[authCounter] ?: "0"
                }
                authCounterFlow.collect {
                    if (it == "0") {
                        isLogged = false
                    }else{
                        authViewModel.giveAuthToken(it)
                    }
                }
            }

            MyNavigation(navController = navController, isLoggedIn = isLogged, context = this)
        }
    }
}
