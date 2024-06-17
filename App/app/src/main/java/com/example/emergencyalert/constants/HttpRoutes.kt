package com.example.emergencyalert.constants

object HttpRoutes {
    private const val BASE_URL = "http://backend:5000"
//    private const val BASE_URL = "http://192.168.70.221:5000"
    const val REGISTER_USER = "$BASE_URL/api/user/createUser"
    const val LOGIN_USER = "$BASE_URL/api/user/login"
    const val GET_HOSPITALS = "$BASE_URL/api/hospital/getHospitals"
    const val GET_HOSPITALS_NEAREST = "$BASE_URL/api/hospital/getHospitalNearest"
    const val GENERATE_ACCIDENT = "$BASE_URL/api/accident/createAccident"
    const val ADD_MED_INFO = "$BASE_URL/api/user/updateUser"
    const val ADD_CONTACTS = "$BASE_URL/api/user/updateUser"
    const val GET_USER_INFO = "$BASE_URL/api/user/getUserInfo"
    const val GET_BLOOD_DONORS_NEAREST = "$BASE_URL/api/bloodDonor/getBloodDonorsNearest"
    const val CREATE_BLOOD_DONOR = "$BASE_URL/api/bloodDonor/createBloodDonor"
}
