package com.suslanium.hackathon.statements.presentation

import com.suslanium.hackathon.statements.data.model.StatementResponseDto

sealed interface StatementsUiState {

    data object Initial : StatementsUiState

    data object Loading : StatementsUiState

    data object Error : StatementsUiState

    data class Success(val statements: List<StatementResponseDto>) : StatementsUiState

}