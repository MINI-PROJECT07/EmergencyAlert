package com.example.emergencyalert

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
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
import com.example.emergencyalert.sensor.SensorViewModel
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


            var isLoggedin by remember { mutableStateOf(true) }
            val navController = rememberNavController()
            LaunchedEffect(true) {
                val auth_counter = stringPreferencesKey("auth_counter")
                val authCounterFlow: Flow<String> = dataStore.data.map { preferences ->
                    preferences[auth_counter] ?: "0"
                }
                authCounterFlow.collect {
                    if (it == "0") {
                        isLoggedin = false
                    }
                }
            }

            MyNavigation(navController = navController, isLoggedIn = isLoggedin, context = this)
        }
    }
}
