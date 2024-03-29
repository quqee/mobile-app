package com.suslanium.hackathon.createdefect.presentation.ui.state

sealed interface CreateDefectScreenState {

    data object Loading : CreateDefectScreenState

    data object Content : CreateDefectScreenState

    data object Error : CreateDefectScreenState

}