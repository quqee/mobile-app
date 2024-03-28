package com.suslanium.hackathon.core.data.model

import com.google.gson.annotations.SerializedName

data class JwtResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String
)
