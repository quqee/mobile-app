package com.suslanium.hackathon.statements.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suslanium.hackathon.auth.data.repository.AuthRepository
import com.suslanium.hackathon.statements.data.repository.StatementRepository
import com.suslanium.hackathon.statements.presentation.state.StatementsUiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException

class StatementsViewModel(
    private val authRepository: AuthRepository,
    private val statementRepository: StatementRepository
) : ViewModel() {

    private val _statementsUiState = MutableStateFlow<StatementsUiState>(StatementsUiState.Initial)
    val statementsUiState = _statementsUiState.asStateFlow()

    private val statementsExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> {
                when (exception.code()) {
                    401 -> {
                        viewModelScope.launch(Dispatchers.IO + finalStatementsExceptionHandler) {
                            authRepository.refresh()
                            val statements = statementRepository.getMyStatements()
                            _statementsUiState.update { StatementsUiState.Success(statements) }
                        }
                    }
                    else -> _statementsUiState.update { StatementsUiState.Error }
                }
            }
            else -> _statementsUiState.update { StatementsUiState.Error }
        }
    }

    private val finalStatementsExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _statementsUiState.update { StatementsUiState.Error }
    }

    init {
        _statementsUiState.update { StatementsUiState.Loading }
        viewModelScope.launch(Dispatchers.IO + statementsExceptionHandler) {
            val statements = statementRepository.getMyStatements()
            _statementsUiState.update { StatementsUiState.Success(statements) }
        }
    }

    fun onRetry() {
        // TODO
    }

}