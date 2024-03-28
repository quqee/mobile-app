package com.suslanium.hackathon.statements.data.repository

import com.suslanium.hackathon.statements.data.api.StatementApiService
import com.suslanium.hackathon.statements.data.model.AdditionalStatementResponseDto
import com.suslanium.hackathon.statements.data.model.StatementResponseDto

class StatementRepository(
    private val statementApiService: StatementApiService
) {

    suspend fun getMyStatements(): List<StatementResponseDto> {
        return statementApiService.getMyStatements()
    }

    suspend fun getStatementInfo(statementId: String): AdditionalStatementResponseDto {
        return statementApiService.getStatementInfo(statementId)
    }

}