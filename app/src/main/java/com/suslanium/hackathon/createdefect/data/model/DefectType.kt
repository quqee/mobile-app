package com.suslanium.hackathon.createdefect.data.model

import com.google.gson.annotations.SerializedName

data class DefectType(
    val id: String,
    val name: String,
    @SerializedName("has_distance") val hasDistance: Boolean
)