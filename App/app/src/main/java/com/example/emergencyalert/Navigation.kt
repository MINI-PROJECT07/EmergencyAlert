package com.example.emergencyalert

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.emergencyalert.routes.Screens
import com.example.emergencyalert.sensor.SensorViewModel


@Composable
fun MyNavigation(navController: NavHostController,isLoggedIn:Boolean,context: Context) {
    val viewModel = viewModel<SensorViewModel>()
    NavHost(
        navController = navController,
        startDestination = if(!isLoggedIn)  Screens.Login.route else Screens.Home.route ,
    ) {
        composable(Screens.Home.route) {
            HomeScreen(navController,context,viewModel)
        }
        composable(Screens.Login.route) {
            LoginScreen(navController = navController,context)
        }
        composable(Screens.SignUp.route) {
            SignUpScreen(navController = navController,context)
        }
    }

}