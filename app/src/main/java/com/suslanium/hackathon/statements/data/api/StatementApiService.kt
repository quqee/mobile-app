package com.suslanium.hackathon.statements.data.api

import com.suslanium.hackathon.statements.data.model.AdditionalStatementResponseDto
import com.suslanium.hackathon.statements.data.model.StatementResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface StatementApiService {

    @GET("statements/my")
    suspend fun getMyStatements(): List<StatementResponseDto>

    @GET("statements/{statementId}")
    suspend fun getStatementInfo(
        @Path("statementId") statementId: String
    ): AdditionalStatementResponseDto

}