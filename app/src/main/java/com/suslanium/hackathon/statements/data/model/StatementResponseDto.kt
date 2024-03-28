package com.suslanium.hackathon.statements.data.model

import com.google.gson.annotations.SerializedName

data class StatementResponseDto(
    @SerializedName("statement_id")
    val statementId: String,
    @SerializedName("area_name")
    val areaName: String,
    val length: Double,
    @SerializedName("road_type")
    val roadType: RoadType,
    @SerializedName("surface_type")
    val surfaceType: SurfaceType,
    val direction: String,
    val deadline: String,
    @SerializedName("create_time")
    val createTime: String,
    val description: String,
    @SerializedName("statement_status")
    val statementStatus: StatementStatus
)
