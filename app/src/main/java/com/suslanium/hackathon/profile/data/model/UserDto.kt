package com.suslanium.hackathon.profile.data.model

import com.google.gson.annotations.SerializedName

data class UserDto(
    val id: String,
    @SerializedName("full_name")
    val fullName: String,
    val email: String
)
