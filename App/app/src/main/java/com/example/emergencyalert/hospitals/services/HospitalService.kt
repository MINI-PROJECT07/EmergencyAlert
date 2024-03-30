package com.example.emergencyalert.hospitals.services

import com.example.emergencyalert.hospitals.dto.Hospital
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

interface HospitalService {
    suspend fun getHospitals(authToken:String) : List<Hospital>

    companion object {
        fun create(): HospitalService {
            return HospitalServiceImpl(client = HttpClient{
                install(Logging) {
                    level = LogLevel.ALL
                }
                install(ContentNegotiation){
                    json(Json{
                        prettyPrint = true
                        isLenient = true
                    })
                }
            })
        }
    }
}