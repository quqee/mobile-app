package com.suslanium.hackathon.statements.data.model

data class ShortDefectDto(
    val latitude: Double,
    val longitude: Double,
    val id: String,
    val status: StatementStatus,
    val type: String,
    val description: String?
)
