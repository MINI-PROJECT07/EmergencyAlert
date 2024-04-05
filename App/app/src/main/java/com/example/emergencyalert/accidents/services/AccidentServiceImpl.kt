package com.example.emergencyalert.accidents.services

import com.example.emergencyalert.accidents.dto.CreateAccident
import com.example.emergencyalert.accidents.dto.CreateAccidentResponse
import com.example.emergencyalert.constants.HttpRoutes
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AccidentServiceImpl(
    private val client: HttpClient
):AccidentService {
    override suspend fun generateAccident(
        authToken: String,
        createAccident: CreateAccident
    ): CreateAccidentResponse {
        return client.post(HttpRoutes.GENERATE_ACCIDENT){
            headers{
                append("authToken", authToken)
            }
            contentType(ContentType.Application.Json)
            setBody(createAccident)
        }.body()
    }

}