package com.example.emergencyalert.routes

sealed class Screens(val route:String) {
    object Home: Screens("home")
    object Login: Screens("login")
    object SignUp: Screens("signup")
    object Hospitals: Screens("hospitals")
    object Profile: Screens("profile")
    object History: Screens("history")
    object AddMedInfo: Screens("addmedinfo")
    object Settings: Screens("settings")
    object About: Screens("about")
    object Help: Screens("help")
}