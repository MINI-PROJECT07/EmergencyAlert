package com.example.emergencyalert.userauth.services

import com.example.emergencyalert.constants.HttpRoutes
import com.example.emergencyalert.userauth.dto.AuthToken
import com.example.emergencyalert.userauth.dto.UserLogin
import com.example.emergencyalert.userauth.dto.UserRegistration
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.http.*

class AuthServiceImpl(
    private val client:HttpClient
):AuthService {
    override suspend fun registerUser(userInfo: UserRegistration): AuthToken {
        return client.post(HttpRoutes.REGISTER_USER){
            contentType(ContentType.Application.Json)
            setBody(userInfo)
        }.body()
    }

    override suspend fun loginUser(userInfo: UserLogin): AuthToken {
        return client.post(HttpRoutes.LOGIN_USER){
            contentType(ContentType.Application.Json)
            setBody(userInfo)
        }.body()
    }
}