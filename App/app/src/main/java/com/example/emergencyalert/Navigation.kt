package com.example.emergencyalert

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.emergencyalert.hospitals.HospitalScreen
import com.example.emergencyalert.routes.Screens
import com.example.emergencyalert.screens.HomeScreen
import com.example.emergencyalert.screens.LoginScreen
import com.example.emergencyalert.screens.SignUpScreen
import com.example.emergencyalert.sensor.SensorViewModel


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun MyNavigation(navController: NavHostController,isLoggedIn:Boolean,context: Context) {
    val sensorviewModel = viewModel<SensorViewModel>()
    NavHost(
        navController = navController,
        startDestination = if(!isLoggedIn)  Screens.Login.route else Screens.Home.route ,
    ) {
        composable(Screens.Home.route) {
            HomeScreen(navController,context,sensorviewModel)
        }
        composable(Screens.Login.route) {
            LoginScreen(navController = navController,context)
        }
        composable(Screens.SignUp.route) {
            SignUpScreen(navController = navController,context)
        }
        composable(Screens.Hospitals.route){
            HospitalScreen(context = context)
        }
    }

}