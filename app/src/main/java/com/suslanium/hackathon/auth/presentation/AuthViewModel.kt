package com.suslanium.hackathon.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suslanium.hackathon.auth.data.model.LoginRequest
import com.suslanium.hackathon.auth.data.repository.AuthRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException

class AuthViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _authUiState = MutableStateFlow<AuthUiState>(AuthUiState.Initial)
    val authUiState = _authUiState.asStateFlow()

    private val _authState = MutableStateFlow(AuthState())
    val authState = _authState.asStateFlow()

    private val authExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> {
                when (exception.code()) {
                    401 -> {
                        viewModelScope.launch(Dispatchers.IO + finalAuthExceptionHandler) {
                            authRepository.refresh()
                            onLogin()
                            _authUiState.update { AuthUiState.Success }
                        }
                    }
                    else -> _authUiState.update { AuthUiState.Error }
                }
            }
            else -> _authUiState.update { AuthUiState.Error }
        }
    }

    private val finalAuthExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _authUiState.update { AuthUiState.Error }
    }

    fun onEmailChange(email: String) {
        _authState.update {
            it.copy(email = email)
        }
    }

    fun onPasswordChange(password: String) {
        _authState.update {
            it.copy(password = password)
        }
    }

    fun onPasswordVisibilityChange() {
        _authState.update {
            it.copy(isPasswordHidden = !it.isPasswordHidden)
        }
    }

    fun onLogin() {
        _authUiState.update { AuthUiState.Loading }
        viewModelScope.launch(Dispatchers.IO + authExceptionHandler) {
            authRepository.login(
                LoginRequest(
                    email = _authState.value.email,
                    password = _authState.value.password
                )
            )
            _authUiState.update { AuthUiState.Success }
        }
    }

}