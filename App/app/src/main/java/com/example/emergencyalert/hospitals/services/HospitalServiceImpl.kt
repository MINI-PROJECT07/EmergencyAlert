package com.example.emergencyalert.hospitals.services

import com.example.emergencyalert.constants.HttpRoutes
import com.example.emergencyalert.hospitals.dto.Hospital
import com.example.emergencyalert.location.LatLong
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class HospitalServiceImpl(
    private val client: HttpClient
) :HospitalService {
    override suspend fun getHospitals(authToken: String): List<Hospital> {
        return client.get(HttpRoutes.GET_HOSPITALS){
            headers{
                append("authToken", authToken)
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun getHospitalsNearest(authToken: String,latLong: LatLong): List<Hospital> {
        return client.post(HttpRoutes.GET_HOSPITALS_NEAREST){
            headers{
                append("authToken",authToken)
            }
            contentType(ContentType.Application.Json)
            setBody(latLong)
        }.body()
    }
}