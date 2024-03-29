package com.suslanium.hackathon.statements.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suslanium.hackathon.auth.data.repository.AuthRepository
import com.suslanium.hackathon.statements.data.repository.StatementRepository
import com.suslanium.hackathon.statements.presentation.state.StatementUiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException

class StatementViewModel(
    private val statementId: String,
    private val authRepository: AuthRepository,
    private val statementRepository: StatementRepository
) : ViewModel() {

    private val _statementUiState = MutableStateFlow<StatementUiState>(StatementUiState.Initial)
    val statementUiState = _statementUiState.asStateFlow()

    private val statementExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> {
                when (exception.code()) {
                    401 -> {
                        viewModelScope.launch(Dispatchers.IO + finalStatementExceptionHandler) {
                            authRepository.refresh()
                            val statement = statementRepository.getStatementInfo(statementId)
                            _statementUiState.update { StatementUiState.Success(statement) }
                        }
                    }
                    else -> _statementUiState.update { StatementUiState.Error }
                }
            }
            else -> _statementUiState.update { StatementUiState.Error }
        }
    }

    private val finalStatementExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _statementUiState.update { StatementUiState.Error }
    }

    init {
        _statementUiState.update { StatementUiState.Loading }
        viewModelScope.launch(Dispatchers.IO + statementExceptionHandler) {
            val statement = statementRepository.getStatementInfo(statementId)
            _statementUiState.update { StatementUiState.Success(statement) }
        }
    }

    fun onRetry() {
        _statementUiState.update { StatementUiState.Loading }
        viewModelScope.launch(Dispatchers.IO + statementExceptionHandler) {
            val statement = statementRepository.getStatementInfo(statementId)
            _statementUiState.update { StatementUiState.Success(statement) }
        }
    }

}