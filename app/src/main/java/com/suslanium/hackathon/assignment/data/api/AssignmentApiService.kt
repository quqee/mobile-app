package com.suslanium.hackathon.assignment.data.api

import com.suslanium.hackathon.assignment.data.model.AssignmentResponse
import retrofit2.http.PUT

interface AssignmentApiService {
    @PUT("statements")
    suspend fun updateStatement(): AssignmentResponse
}