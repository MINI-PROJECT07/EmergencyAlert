package com.example.emergencyalert.routes

sealed class Screens(val route:String) {
    object Home: Screens("home")
    object Login: Screens("login")
    object SignUp: Screens("signup")
    object Hospitals: Screens("hospitals")
    object Profile: Screens("profile")
    object FirstAid: Screens("firstaid")
    object AddMedInfo: Screens("addmedinfo")
    object EditMedInfo: Screens("editmedinfo")
    object Settings: Screens("settings")
    object About: Screens("about")
    object Help: Screens("help")
    object AddContacts: Screens("addcontacts")
    object EditContacts: Screens("editcontacts")
    object Contacts: Screens("contacts")
    object Donors: Screens("donors")
    object AddBloodDonor: Screens("addblooddonor")
}