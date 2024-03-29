package com.suslanium.hackathon.statements.data.model

import com.google.gson.annotations.SerializedName

data class CreateStatementDto(
    @SerializedName("area_name")
    val areaName: String,
    val length: Double,
    @SerializedName("road_type")
    val roadType: RoadType,
    @SerializedName("surface_type")
    val surfaceType: SurfaceType,
    val direction: String,
)
