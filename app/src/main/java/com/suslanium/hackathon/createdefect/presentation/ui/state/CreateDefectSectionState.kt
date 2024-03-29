package com.suslanium.hackathon.createdefect.presentation.ui.state

sealed interface CreateDefectSectionState {

    data object GeneralContent : CreateDefectSectionState

    data object DefectTypeSelection : CreateDefectSectionState

}