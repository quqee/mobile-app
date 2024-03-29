package com.suslanium.hackathon.createdefect.presentation.ui.state

sealed interface DefectCreationEvent {

    data object Success : DefectCreationEvent

    data object Error : DefectCreationEvent

}