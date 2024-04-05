package com.example.emergencyalert.screens.useroperations

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.emergencyalert.Buttoncomponent
import com.example.emergencyalert.ClickableLoginTextComponent
import com.example.emergencyalert.Mytextfield
import com.example.emergencyalert.PasswordMytextfield
import com.example.emergencyalert.R
import com.example.emergencyalert.TextBold
import com.example.emergencyalert.dataStore
import com.example.emergencyalert.routes.Screens
import com.example.emergencyalert.userauth.dto.UserRegistration
import com.example.emergencyalert.userauth.services.AuthService
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(navController: NavController, context: Context) {
    val authService = AuthService.create()
    var name by remember { mutableStateOf("") }
    var phoneNo by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp),

        ) {
        Column(Modifier.fillMaxSize()) {
            TextBold(value = "Create Account")
            Spacer(modifier = Modifier.height(10.dp))
            name = Mytextfield(
                labelvalue = "Name",
                painterResource(id = R.drawable.account_outline)
            )
            Spacer(modifier = Modifier.height(10.dp))
            phoneNo = Mytextfield(
                labelvalue = "Phone No",
                painterResource = painterResource(id = R.drawable.cellphone)

            )
            Spacer(modifier = Modifier.height(10.dp))
            email = Mytextfield(
                labelvalue = "Email",
                painterResource = painterResource(id = R.drawable.gmail)
            )
            Spacer(modifier = Modifier.height(10.dp))
            password = PasswordMytextfield(
                labelvalue = "Password",
                painterResource = painterResource(id = R.drawable.lock_outline)
            )
            Spacer(modifier = Modifier.height(50.dp))
            Buttoncomponent(
                value = "Register"
            ) {
                val userRegistration = UserRegistration(
                    name = name,
                    phoneNo = phoneNo,
                    email = email,
                    password = password
                )
                scope.launch {
                    val authToken = authService.registerUser(userRegistration)
                    Log.d("RegisterScreen", "RegisterScreen: $authToken")
                    if (authToken.success) {
                        context.dataStore.edit { preferences ->
                            preferences[stringPreferencesKey("auth_counter")] = authToken.authToken
                        }
                        navController.navigate(Screens.Home.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            ClickableLoginTextComponent(tryingToLogin = true, navController)
        }

    }
}


