package com.example.emergencyalert.accidents.services

import com.example.emergencyalert.accidents.dto.CreateAccident
import com.example.emergencyalert.accidents.dto.CreateAccidentResponse
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

interface AccidentService {
    suspend fun generateAccident(
        authToken: String,
        createAccident: CreateAccident
    ): CreateAccidentResponse


    companion object {
        fun create(): AccidentService {
            return AccidentServiceImpl(client = HttpClient {
                install(Logging) {
                    level = LogLevel.ALL
                }
                install(ContentNegotiation) {
                    json(Json {
                        prettyPrint = true
                        isLenient = true
                    })
                }
            })
        }
    }
}