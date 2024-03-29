package com.suslanium.hackathon.assignment.data.model

import com.suslanium.hackathon.statements.data.model.RoadType
import com.suslanium.hackathon.statements.data.model.StatementStatus
import com.suslanium.hackathon.statements.data.model.SurfaceType
import retrofit2.http.Multipart

data class UpdateStatementRequestBody(
    val statementId: String,
    val areaName: String,
    val length: Double,
    val roadType: RoadType,
    val surfaceType: SurfaceType,
    val direction: String,
    val deadline: String,
    val status: StatementStatus,
    val audioFile: Multipart,
    val organizationId: String
)
