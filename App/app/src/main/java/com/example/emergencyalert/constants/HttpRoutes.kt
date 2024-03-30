package com.example.emergencyalert.constants

object HttpRoutes {
    private const val BASE_URL = "http://192.168.97.221:5000"
    const val REGISTER_USER = "$BASE_URL/api/user/createUser"
    const val LOGIN_USER = "$BASE_URL/api/user/login"
    const val GET_HOSPITALS = "$BASE_URL/api/hospital/getHospitals"
}