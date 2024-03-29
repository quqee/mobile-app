package com.suslanium.hackathon.assignment.data.repository

import com.suslanium.hackathon.assignment.data.api.AssignmentApiService
import com.suslanium.hackathon.assignment.data.model.AssignmentResponse

class AssignmentRepository(
    private val assignmentApiService: AssignmentApiService
) {
    suspend fun updateStatements(): AssignmentResponse {
        return assignmentApiService.updateStatement()
    }

}