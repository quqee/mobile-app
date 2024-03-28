package com.suslanium.hackathon.statements.presentation.state

sealed interface CreateStatementUiState {

    data object Initial : CreateStatementUiState

    data object Loading : CreateStatementUiState

    data object Error : CreateStatementUiState

    data object Success : CreateStatementUiState

}