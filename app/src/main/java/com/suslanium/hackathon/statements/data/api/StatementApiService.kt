package com.suslanium.hackathon.statements.data.api

import com.suslanium.hackathon.statements.data.model.StatementResponseDto
import retrofit2.http.GET

interface StatementApiService {

    @GET("statements/my")
    suspend fun getMyStatements(): List<StatementResponseDto>

}