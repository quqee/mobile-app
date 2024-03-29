package com.suslanium.hackathon.profile.data.repository

import com.suslanium.hackathon.profile.data.api.ProfileApiService
import com.suslanium.hackathon.profile.data.model.UserDto

class ProfileRepository(
    private val profileApiService: ProfileApiService
) {

    suspend fun getProfile(): UserDto = profileApiService.getProfile()

}