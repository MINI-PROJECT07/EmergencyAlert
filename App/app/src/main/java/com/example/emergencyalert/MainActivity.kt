package com.example.emergencyalert

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.rememberNavController
import com.example.emergencyalert.routes.Screens
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_info")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            LaunchedEffect(this){
                val auth_counter = stringPreferencesKey("auth_counter")
                val authCounterFlow: Flow<String> = dataStore.data.map { preferences ->
                    preferences[auth_counter] ?: "0"
                }
                authCounterFlow.collect {
                    if(it=="0"){
                        navController.navigate(Screens.Login.route)
                    }
                }
            }
            MyNavigation(navController = navController)
        }
    }
}
