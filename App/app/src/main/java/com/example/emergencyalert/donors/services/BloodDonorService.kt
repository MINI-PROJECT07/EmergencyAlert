package com.example.emergencyalert.donors.services

import com.example.emergencyalert.donors.dto.BloodDonorInfo
import com.example.emergencyalert.donors.dto.CreateBloodDonor
import com.example.emergencyalert.donors.dto.GetBloodDonors
import com.example.emergencyalert.location.LatLong
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

interface BloodDonorService {
    suspend fun createBloodDonor(
        authToken: String,
        createBloodDonor: CreateBloodDonor
    )
    suspend fun getBloodDonorsNearest(
        latLong: LatLong
    ):List<BloodDonorInfo>


    companion object {
        fun create(): BloodDonorService {
            return BloodDonorServiceImpl(
                client = HttpClient {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(ContentNegotiation) {
                        json(Json {
                            prettyPrint = true
                            isLenient = true
                        })
                    }
                }
            )
        }
    }
}