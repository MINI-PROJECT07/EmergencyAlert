package com.example.emergencyalert.userauth.services

import com.example.emergencyalert.constants.HttpRoutes
import com.example.emergencyalert.userauth.dto.AddContacts
import com.example.emergencyalert.userauth.dto.AuthToken
import com.example.emergencyalert.userauth.dto.UserInfo
import com.example.emergencyalert.userauth.dto.UserLogin
import com.example.emergencyalert.userauth.dto.UserMedInfo
import com.example.emergencyalert.userauth.dto.UserRegistration
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.client.utils.EmptyContent.contentType
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

    override suspend fun addMedInfo(userInfo: UserMedInfo,authToken:String):Boolean {
        val response = client.put(HttpRoutes.ADD_MED_INFO){
            headers{
                append("authToken",authToken)
            }
            contentType(ContentType.Application.Json)
            setBody(userInfo)
        }
        println(response)
        return response.status == HttpStatusCode.OK
    }

    override suspend fun addContacts(contacts: AddContacts, authToken: String): Boolean {
        val response = client.put(HttpRoutes.ADD_CONTACTS){
            headers{
                append("authToken",authToken)
            }
            contentType(ContentType.Application.Json)
            setBody(contacts)
        }
        println(response)
        return response.status == HttpStatusCode.OK
    }

    override suspend fun getUserInfo(authToken: String): UserInfo {
        val res = client.get(HttpRoutes.GET_USER_INFO){
            headers{
                append("authToken",authToken)
            }
        }
        return res.body()
    }
}