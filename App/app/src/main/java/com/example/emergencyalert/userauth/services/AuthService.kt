package com.example.emergencyalert.userauth.services

import com.example.emergencyalert.userauth.dto.AuthToken
import com.example.emergencyalert.userauth.dto.UserInfo
import com.example.emergencyalert.userauth.dto.UserLogin
import com.example.emergencyalert.userauth.dto.UserMedInfo
import com.example.emergencyalert.userauth.dto.UserRegistration
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

interface AuthService {
    suspend fun registerUser(userInfo: UserRegistration): AuthToken
    suspend fun loginUser(userInfo: UserLogin): AuthToken

    suspend fun addMedInfo(userInfo: UserMedInfo,authToken:String):Boolean

    suspend fun getUserInfo(authToken: String): UserInfo
    companion object   {
        fun create(): AuthService {
            return AuthServiceImpl(
                client = HttpClient{
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(ContentNegotiation){
                        json(Json{
                            prettyPrint = true
                            isLenient = true
                        })
                    }
                }
            )
        }
    }
}