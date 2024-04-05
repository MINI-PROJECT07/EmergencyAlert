package com.example.emergencyalert.util

import com.example.emergencyalert.location.LatLong

fun generateLocationUrl(latLong: LatLong): String {
    return "https://www.google.com/maps/place/" +
            "${latLong.latitude},${latLong.longitude}/@${latLong.latitude},${latLong.longitude},20z"
}
