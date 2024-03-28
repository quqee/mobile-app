package com.suslanium.hackathon.defect.presentation.ui

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.suslanium.hackathon.createdefect.presentation.ui.screen.components.ErrorContent
import com.suslanium.hackathon.createdefect.presentation.ui.screen.components.LoadingContent
import com.suslanium.hackathon.defect.presentation.state.DefectScreenState
import com.suslanium.hackathon.defect.presentation.ui.components.DefectContent
import com.suslanium.hackathon.defect.presentation.viewmodel.DefectViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun DefectScreen(defectId: String) {
    val viewModel: DefectViewModel = koinViewModel {
        parametersOf(defectId)
    }
    val screenState by remember { viewModel.screenState }
    val content by remember { viewModel.defectContent }

    Crossfade(targetState = screenState, label = "") { state ->
        when(state) {
            DefectScreenState.Content -> {
                content?.let {
                    DefectContent(model = it)
                }
            }
            DefectScreenState.Error -> ErrorContent(viewModel::loadData)
            DefectScreenState.Loading -> LoadingContent()
        }
    }
}