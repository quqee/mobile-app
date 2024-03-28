package com.suslanium.hackathon.statements.presentation.state

import com.suslanium.hackathon.statements.data.model.AdditionalStatementResponseDto

sealed interface StatementUiState {

    data object Initial : StatementUiState

    data object Loading : StatementUiState

    data object Error : StatementUiState

    data class Success(val statement: AdditionalStatementResponseDto) : StatementUiState

}