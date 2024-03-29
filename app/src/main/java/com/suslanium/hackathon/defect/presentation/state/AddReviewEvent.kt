package com.suslanium.hackathon.defect.presentation.state

sealed interface AddReviewEvent {

    data object Success : AddReviewEvent

    data object Error : AddReviewEvent

}