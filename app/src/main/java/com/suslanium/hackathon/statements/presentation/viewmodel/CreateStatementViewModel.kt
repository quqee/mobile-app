package com.suslanium.hackathon.statements.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suslanium.hackathon.auth.data.repository.AuthRepository
import com.suslanium.hackathon.statements.data.model.CreateStatementDto
import com.suslanium.hackathon.statements.data.model.RoadType
import com.suslanium.hackathon.statements.data.model.SurfaceType
import com.suslanium.hackathon.statements.data.repository.StatementRepository
import com.suslanium.hackathon.statements.presentation.state.CreateStatementSection
import com.suslanium.hackathon.statements.presentation.state.CreateStatementState
import com.suslanium.hackathon.statements.presentation.state.CreateStatementUiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException

class CreateStatementViewModel(
    private val authRepository: AuthRepository,
    private val statementRepository: StatementRepository
) : ViewModel() {

    private val _createStatementUiState = MutableStateFlow<CreateStatementUiState>(CreateStatementUiState.Initial)
    val createStatementUiState = _createStatementUiState.asStateFlow()

    private val _createStatementState = MutableStateFlow(CreateStatementState())
    val createStatementState = _createStatementState.asStateFlow()

    private val _createStatementSection = MutableStateFlow<CreateStatementSection>(CreateStatementSection.CreationForm)
    val createStatementSection = _createStatementSection.asStateFlow()

    private val createStatementExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> {
                when (exception.code()) {
                    401 -> {
                        viewModelScope.launch(Dispatchers.IO + finalCreateStatementExceptionHandler) {
                            authRepository.refresh()
                            val roadLength = _createStatementState.value.roadLength
                            val roadType = _createStatementState.value.roadType
                            val surfaceType = _createStatementState.value.surfaceType

                            if (roadLength.toDoubleOrNull() == null ||
                                roadType == null ||
                                surfaceType == null) {
                                _createStatementUiState.update { CreateStatementUiState.Error }
                                return@launch
                            }

                            statementRepository.createStatement(
                                CreateStatementDto(
                                    areaName = _createStatementState.value.areaName,
                                    length = roadLength.toDouble(),
                                    roadType = roadType,
                                    surfaceType = surfaceType,
                                    direction = _createStatementState.value.direction
                                )
                            )
                            _createStatementUiState.update { CreateStatementUiState.Success }
                        }
                    }
                    else -> _createStatementUiState.update { CreateStatementUiState.Error }
                }
            }
            else -> _createStatementUiState.update { CreateStatementUiState.Error }
        }
    }

    private val finalCreateStatementExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _createStatementUiState.update { CreateStatementUiState.Error }
    }

    fun onAreaNameChange(areaName: String) {
        _createStatementState.update {
            it.copy(areaName = areaName)
        }
    }

    fun onRoadLengthChange(roadLength: String) {
        _createStatementState.update {
            it.copy(roadLength = roadLength)
        }
    }

    fun onRoadTypeChange(roadType: RoadType) {
        _createStatementState.update {
            it.copy(roadType = roadType)
        }
    }

    fun onSurfaceTypeChange(surfaceType: SurfaceType) {
        _createStatementState.update {
            it.copy(surfaceType = surfaceType)
        }
    }

    fun onDirectionChange(direction: String) {
        _createStatementState.update {
            it.copy(direction = direction)
        }
    }

    fun openRoadTypeSelection() {
        _createStatementSection.update { CreateStatementSection.RoadTypes }
    }

    fun openSurfaceTypeSelection() {
        _createStatementSection.update { CreateStatementSection.SurfaceTypes }
    }

    fun openCreationForm() {
        _createStatementSection.update { CreateStatementSection.CreationForm }
    }

    fun onCreateStatement() {
        viewModelScope.launch(Dispatchers.IO + createStatementExceptionHandler) {
            val roadLength = _createStatementState.value.roadLength
            val roadType = _createStatementState.value.roadType
            val surfaceType = _createStatementState.value.surfaceType

            if (roadLength.toDoubleOrNull() == null ||
                roadType == null ||
                surfaceType == null) {
                _createStatementUiState.update { CreateStatementUiState.Error }
                return@launch
            }

            statementRepository.createStatement(
                CreateStatementDto(
                    areaName = _createStatementState.value.areaName,
                    length = roadLength.toDouble(),
                    roadType = roadType,
                    surfaceType = surfaceType,
                    direction = _createStatementState.value.direction
                )
            )
            _createStatementUiState.update { CreateStatementUiState.Success }
        }
    }

}