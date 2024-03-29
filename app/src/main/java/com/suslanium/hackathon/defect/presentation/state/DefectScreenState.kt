package com.suslanium.hackathon.defect.presentation.state

sealed interface DefectScreenState {

    data object Content: DefectScreenState

    data object Loading: DefectScreenState

    data object Error : DefectScreenState

}