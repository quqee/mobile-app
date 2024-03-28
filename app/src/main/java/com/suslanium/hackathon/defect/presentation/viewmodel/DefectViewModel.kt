package com.suslanium.hackathon.defect.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suslanium.hackathon.defect.data.model.DefectModel
import com.suslanium.hackathon.defect.data.repository.DefectRepository
import com.suslanium.hackathon.defect.presentation.state.DefectScreenState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DefectViewModel(
    private val defectId: String, private val defectRepository: DefectRepository
) : ViewModel() {

    val screenState: State<DefectScreenState>
        get() = _screenState
    private var _screenState = mutableStateOf<DefectScreenState>(DefectScreenState.Loading)

    val defectContent: State<DefectModel?>
        get() = _defectContent
    private var _defectContent = mutableStateOf<DefectModel?>(null)

    private val loadingExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _screenState.value = DefectScreenState.Error
    }

    init {
        loadData()
    }

    fun loadData() {
        _screenState.value = DefectScreenState.Loading
        viewModelScope.launch(Dispatchers.IO + loadingExceptionHandler) {
            val defect = defectRepository.getDefectInfo(defectId)
            withContext(Dispatchers.Main) {
                _defectContent.value = defect
                _screenState.value = DefectScreenState.Content
            }
        }
    }
}