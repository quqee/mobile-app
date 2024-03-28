package com.suslanium.hackathon.statements.data.model

data class AdditionalStatementResponseDto(
    val statementId: String,
    val areaName: String,
    val length: Double,
    val roadType: RoadType,
    val surfaceType: SurfaceType,
    val direction: String,
    val deadline: String,
    val createTime: String,
    val description: String,
    val status: StatementStatus,
    val defects: List<ShortDefectDto>
)
