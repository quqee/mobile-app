package com.suslanium.hackathon.profile.data.api

import com.suslanium.hackathon.profile.data.model.UserDto
import retrofit2.http.GET

interface ProfileApiService {

    @GET("users/my/profile")
    suspend fun getProfile(): UserDto

}