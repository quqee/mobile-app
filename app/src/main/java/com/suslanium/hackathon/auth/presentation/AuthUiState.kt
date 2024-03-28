package com.suslanium.hackathon.auth.presentation

sealed interface AuthUiState {

    data object Initial : AuthUiState

    data object Loading : AuthUiState

    data object Error : AuthUiState

    data object Success : AuthUiState

}