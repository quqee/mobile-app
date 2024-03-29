package com.suslanium.hackathon.profile.presentation.state

import com.suslanium.hackathon.profile.data.model.UserDto

sealed interface ProfileUiState {

    data object Initial : ProfileUiState

    data object Loading : ProfileUiState

    data object Error : ProfileUiState

    data class Success(val profile: UserDto) : ProfileUiState

}