package com.suslanium.hackathon.defect.presentation.ui

import android.widget.Toast
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.suslanium.hackathon.R
import com.suslanium.hackathon.createdefect.presentation.ui.common.ObserveAsEvents
import com.suslanium.hackathon.createdefect.presentation.ui.screen.components.LoadingContent
import com.suslanium.hackathon.defect.presentation.state.AddReviewEvent
import com.suslanium.hackathon.defect.presentation.state.AddReviewScreenState
import com.suslanium.hackathon.defect.presentation.ui.components.AddReviewContent
import com.suslanium.hackathon.defect.presentation.viewmodel.AddReviewViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun AddReviewScreen(defectId: String, onBack: () -> Unit, onNavigateAfterSuccess: () -> Unit) {
    val viewModel: AddReviewViewModel = koinViewModel {
        parametersOf(defectId)
    }
    val screenState by remember { viewModel.screenState }
    val imageUris = remember { viewModel.imageUris }
    val context = LocalContext.current

    ObserveAsEvents(flow = viewModel.creationEvents) {
        when (it) {
            AddReviewEvent.Error -> Toast.makeText(
                context, R.string.generic_error_msg, Toast.LENGTH_LONG
            ).show()

            AddReviewEvent.Success -> onNavigateAfterSuccess()
        }
    }

    Crossfade(targetState = screenState, label = "") {
        when (it) {
            AddReviewScreenState.Content -> AddReviewContent(
                onBack = onBack,
                imageUris = imageUris,
                onUploadClick = viewModel::submitPhotos)
            AddReviewScreenState.Loading -> LoadingContent()
        }
    }
}