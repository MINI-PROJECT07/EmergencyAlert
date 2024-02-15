package com.example.emergencyalert.routes

sealed class Screens(val route:String) {
    object Home: Screens("home")
    object Login: Screens("login")
    object Register: Screens("register")
    object Profile: Screens("profile")
    object Settings: Screens("settings")
    object About: Screens("about")
    object Help: Screens("help")
}