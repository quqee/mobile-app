package com.suslanium.hackathon.statements.data.model

data class ShortDefectDto(
    val id: String,
    val status: StatementStatus,
    val type: String,
    val description: String
)
