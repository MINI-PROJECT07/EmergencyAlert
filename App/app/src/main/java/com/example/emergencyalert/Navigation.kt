package com.example.emergencyalert

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Bloodtype
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.emergencyalert.hospitals.HospitalViewModel
import com.example.emergencyalert.screens.HospitalScreen
import com.example.emergencyalert.routes.Screens
import com.example.emergencyalert.screens.HistoryScreen
import com.example.emergencyalert.screens.HomeScreen
import com.example.emergencyalert.screens.LoginScreen
import com.example.emergencyalert.screens.SignUpScreen
import com.example.emergencyalert.sensor.SensorViewModel
import com.example.emergencyalert.screens.ProfileScreen


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun MyNavigation(navController: NavHostController, isLoggedIn: Boolean, context: Context) {
    val sensorviewModel = viewModel<SensorViewModel>()
    val hospitalViewModel = viewModel<HospitalViewModel>()
    NavHost(
        navController = navController,
        startDestination = if (!isLoggedIn) Screens.Login.route else Screens.Home.route,
    ) {
        composable(Screens.Home.route) {
            HomeScreen(navController, context, sensorviewModel)
        }
        composable(Screens.Login.route) {
            LoginScreen(navController = navController, context)
        }
        composable(Screens.SignUp.route) {
            SignUpScreen(navController = navController, context)
        }
        composable(Screens.Hospitals.route) {
            HospitalScreen(context = context,hospitalViewModel)
        }
        composable(Screens.Profile.route) {
            ProfileScreen(context = context)
        }
        composable(Screens.History.route){
            HistoryScreen(context = context)
        }
    }

}

@Composable
fun MyNavBar(navController: NavHostController) {
    var selectedItem by remember {
        mutableStateOf(2)
    }
    NavigationBar {
        BottomNavItem().bottomNavItems().forEachIndexed { ind, navItem ->
            NavigationBarItem(selected = (selectedItem == ind), onClick = {
                selectedItem = ind
                navController.navigate(navItem.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }, icon = {
                Icon(imageVector = navItem.icon, contentDescription = navItem.title)
            },
                label = {
                    Text(text = navItem.title, textAlign = TextAlign.Center)
                }
            )
        }
    }
}

data class BottomNavItem(
    val icon: ImageVector = Icons.Filled.Home,
    val route: String = "",
    val title: String = ""
) {
    fun bottomNavItems(): List<BottomNavItem> {
        return listOf(
            BottomNavItem(
                icon = Icons.Filled.AccountCircle,
                route = Screens.Profile.route,
                title = "Profile"
            ),
            BottomNavItem(
                icon = Icons.Filled.LocalHospital,
                route = Screens.Hospitals.route,
                title = "Help"
            ),
            BottomNavItem(
                icon = Icons.Filled.Home,
                route = Screens.Home.route,
                title = "Home"
            ),
            BottomNavItem(
                icon = Icons.Filled.Bloodtype,
                route = Screens.Profile.route,
                title = "Donors"
            ),
            BottomNavItem(
                icon = Icons.Filled.History,
                route = Screens.History.route,
                title = "History"
            )
        )
    }
}