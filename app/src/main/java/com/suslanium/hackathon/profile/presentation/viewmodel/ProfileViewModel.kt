package com.suslanium.hackathon.profile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suslanium.hackathon.auth.data.repository.AuthRepository
import com.suslanium.hackathon.profile.data.repository.ProfileRepository
import com.suslanium.hackathon.profile.presentation.state.ProfileUiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ProfileViewModel(
    private val profileRepository: ProfileRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _profileUiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Initial)
    val profileUiState = _profileUiState.asStateFlow()

    private val profileExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> {
                when (exception.code()) {
                    401 -> {
                        viewModelScope.launch(Dispatchers.IO + finalProfileExceptionHandler) {
                            authRepository.refresh()
                            val profile = profileRepository.getProfile()
                            _profileUiState.update { ProfileUiState.Success(profile) }
                        }
                    }
                    else -> _profileUiState.update { ProfileUiState.Error }
                }
            }
            else -> _profileUiState.update { ProfileUiState.Error }
        }
    }

    private val finalProfileExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _profileUiState.update { ProfileUiState.Error }
    }

    init {
        viewModelScope.launch(Dispatchers.IO + profileExceptionHandler) {
            val profile = profileRepository.getProfile()
            _profileUiState.update { ProfileUiState.Success(profile) }
        }
    }

    fun onRetry() {
        viewModelScope.launch(Dispatchers.IO + profileExceptionHandler) {
            val profile = profileRepository.getProfile()
            _profileUiState.update { ProfileUiState.Success(profile) }
        }
    }

    fun logoutUser() {
        viewModelScope.launch(Dispatchers.IO + profileExceptionHandler) {
            authRepository.logout()
        }
    }
}