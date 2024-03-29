package com.suslanium.hackathon.defect.presentation.viewmodel

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suslanium.hackathon.defect.data.repository.DefectRepository
import com.suslanium.hackathon.defect.presentation.state.AddReviewEvent
import com.suslanium.hackathon.defect.presentation.state.AddReviewScreenState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddReviewViewModel(
    private val defectId: String, private val defectRepository: DefectRepository
) : ViewModel() {

    val imageUris = mutableStateListOf<Uri>()

    val screenState: State<AddReviewScreenState>
        get() = _screenState
    private var _screenState = mutableStateOf<AddReviewScreenState>(AddReviewScreenState.Content)

    private val _creationChannel = Channel<AddReviewEvent>()
    val creationEvents = _creationChannel.receiveAsFlow()

    private var loadingExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _screenState.value = AddReviewScreenState.Content
        _creationChannel.trySend(AddReviewEvent.Error)
    }

    fun submitPhotos() {
        _screenState.value = AddReviewScreenState.Loading
        viewModelScope.launch(Dispatchers.IO + loadingExceptionHandler) {
            defectRepository.markDefectAsCompleted(defectId, imageUris)
            withContext(Dispatchers.Main) {
                _creationChannel.send(AddReviewEvent.Success)
            }
        }
    }

}