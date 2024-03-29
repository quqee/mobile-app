package com.suslanium.hackathon.core.data.api

import com.suslanium.hackathon.auth.data.model.LoginRequest
import com.suslanium.hackathon.core.data.model.JwtResponse
import com.suslanium.hackathon.core.data.model.RefreshRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): JwtResponse

    @POST("auth/refresh")
    suspend fun refresh(@Body refreshRequest: RefreshRequest): JwtResponse

    @POST("auth/logout")
    suspend fun logout(@Body refreshRequest: RefreshRequest)

}