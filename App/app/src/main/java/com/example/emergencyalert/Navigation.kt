package com.example.emergencyalert

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.emergencyalert.routes.Screens


@Composable
fun MyNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "",
    ) {
        composable(Screens.Home.route) {
            HomeScreen()
        }

    }

}