package com.example.emergencyalert.donors.services

import com.example.emergencyalert.constants.HttpRoutes
import com.example.emergencyalert.donors.dto.BloodDonorInfo
import com.example.emergencyalert.donors.dto.CreateBloodDonor
import com.example.emergencyalert.donors.dto.GetBloodDonors
import com.example.emergencyalert.location.LatLong
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class BloodDonorServiceImpl (
    private val client: HttpClient
): BloodDonorService {
    override suspend fun createBloodDonor(authToken: String, createBloodDonor: CreateBloodDonor) {
        val res = client.post(HttpRoutes.CREATE_BLOOD_DONOR){
            headers{
                append("authToken", authToken)
            }
            contentType(ContentType.Application.Json)
            setBody(createBloodDonor)
        }.body<String>()
    }

    override suspend fun getBloodDonorsNearest(
        latLong: LatLong
    ): List<BloodDonorInfo> {
        val res =  client.post(HttpRoutes.GET_BLOOD_DONORS_NEAREST){
            contentType(ContentType.Application.Json)
            setBody(latLong)
        }.body<List<BloodDonorInfo>>()
        println("Hi${res}")
        return res
    }
}