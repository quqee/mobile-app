package com.suslanium.hackathon.createdefect.presentation

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suslanium.hackathon.createdefect.data.model.CreateDefectModel
import com.suslanium.hackathon.createdefect.data.model.DefectType
import com.suslanium.hackathon.createdefect.data.repository.CreateDefectRepository
import com.suslanium.hackathon.createdefect.presentation.ui.state.CreateDefectScreenState
import com.suslanium.hackathon.createdefect.presentation.ui.state.CreateDefectSectionState
import com.suslanium.hackathon.createdefect.presentation.ui.state.DefectCreationEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateDefectViewModel(private val repository: CreateDefectRepository = CreateDefectRepository()) :
    ViewModel() {

    val latitude: State<Double?>
        get() = _latitude
    private var _latitude = mutableStateOf<Double?>(null)

    val longitude: State<Double?>
        get() = _longitude
    private var _longitude = mutableStateOf<Double?>(null)

    val defectType: State<DefectType?>
        get() = _defectType
    private var _defectType = mutableStateOf<DefectType?>(null)

    val defectDistance: State<String>
        get() = _defectDistance
    private var _defectDistance = mutableStateOf("")

    val fileUris = mutableStateListOf<Uri>()

    val defectTypes: State<List<DefectType>>
        get() = _defectTypes
    private var _defectTypes = mutableStateOf<List<DefectType>>(emptyList())

    val screenState: State<CreateDefectScreenState>
        get() = _screenState
    private var _screenState =
        mutableStateOf<CreateDefectScreenState>(CreateDefectScreenState.Loading)

    val sectionState: State<CreateDefectSectionState>
        get() = _sectionState
    private var _sectionState =
        mutableStateOf<CreateDefectSectionState>(CreateDefectSectionState.GeneralContent)

    val dataIsCorrectlyFilledIn = derivedStateOf {
        latitude.value != null && longitude.value != null && defectType.value != null && fileUris.isNotEmpty() && (defectDistance.value.isNotBlank() && defectDistance.value.toDoubleOrNull() != null || defectType.value?.hasDistance == false)
    }

    val distanceIsCorrectlyFilled = derivedStateOf {
        defectDistance.value.isBlank() || defectDistance.value.toDoubleOrNull() != null
    }

    private val _creationChannel = Channel<DefectCreationEvent>()
    val creationEvents = _creationChannel.receiveAsFlow()

    private val loadingExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _screenState.value = CreateDefectScreenState.Error
    }

    private val creationExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _creationChannel.trySend(DefectCreationEvent.Error)
        _screenState.value = CreateDefectScreenState.Content
    }

    init {
        loadDefectTypes()
    }

    fun loadDefectTypes() {
        _screenState.value = CreateDefectScreenState.Loading
        viewModelScope.launch(Dispatchers.IO + loadingExceptionHandler) {
            val defectTypes = repository.getDefectTypes()
            withContext(Dispatchers.Main) {
                _defectTypes.value = defectTypes
                _screenState.value = CreateDefectScreenState.Content
            }
        }
    }

    fun openTypeSelection() {
        _sectionState.value = CreateDefectSectionState.DefectTypeSelection
    }

    fun openGeneralContent() {
        _sectionState.value = CreateDefectSectionState.GeneralContent
    }

    fun createDefect() {
        if (!dataIsCorrectlyFilledIn.value) return
        _screenState.value = CreateDefectScreenState.Loading
        viewModelScope.launch(Dispatchers.IO + creationExceptionHandler) {
            val model = CreateDefectModel(
                latitude = latitude.value!!,
                longitude = longitude.value!!,
                defectType = defectType.value!!,
                defectDistance = defectDistance.value.toDoubleOrNull(),
                fileUris = fileUris
            )
            repository.createRequest(model)
            _creationChannel.send(DefectCreationEvent.Success)
        }
    }

    fun setCoordinates(latitude: Double?, longitude: Double?) {
        _latitude.value = latitude
        _longitude.value = longitude
    }

    fun setDefectType(defectType: DefectType) {
        _defectType.value = defectType
        if (!defectType.hasDistance) _defectDistance.value = ""
    }

    fun setDefectDistance(defectDistance: String) {
        _defectDistance.value = defectDistance
    }

}