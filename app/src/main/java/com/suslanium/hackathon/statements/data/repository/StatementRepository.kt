package com.suslanium.hackathon.statements.data.repository

import com.suslanium.hackathon.statements.data.api.StatementApiService
import com.suslanium.hackathon.statements.data.model.AdditionalStatementResponseDto
import com.suslanium.hackathon.statements.data.model.CreateStatementDto
import com.suslanium.hackathon.statements.data.model.StatementResponseDto
import okhttp3.MultipartBody

class StatementRepository(
    private val statementApiService: StatementApiService
) {

    suspend fun getMyStatements(): List<StatementResponseDto> {
        return statementApiService.getMyStatements()
    }

    suspend fun getStatementInfo(statementId: String): AdditionalStatementResponseDto {
        return statementApiService.getStatementInfo(statementId)
    }

    suspend fun createStatement(createStatementDto: CreateStatementDto) {
        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("area_name", createStatementDto.areaName)
            .addFormDataPart("length", createStatementDto.length.toString())
            .addFormDataPart("road_type", createStatementDto.roadType.toString())
            .addFormDataPart("surface_type", createStatementDto.surfaceType.toString())
            .addFormDataPart("direction", createStatementDto.direction)
            .build()

        statementApiService.createStatement(requestBody)
    }

}