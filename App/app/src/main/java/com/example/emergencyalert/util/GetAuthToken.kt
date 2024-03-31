package com.example.emergencyalert.util

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.emergencyalert.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

class GetAuthToken(){
    fun getAuthToken(context: Context):String= runBlocking{
        val authCounter = stringPreferencesKey("auth_counter")
        val authCounterFlow: Flow<String> = context.dataStore.data.map { preferences ->
            preferences[authCounter] ?: "0"
        }
        authCounterFlow.first()
    }
}