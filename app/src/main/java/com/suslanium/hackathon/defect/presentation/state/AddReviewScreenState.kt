package com.suslanium.hackathon.defect.presentation.state

sealed interface AddReviewScreenState {

    data object Content : AddReviewScreenState

    data object Loading : AddReviewScreenState

}