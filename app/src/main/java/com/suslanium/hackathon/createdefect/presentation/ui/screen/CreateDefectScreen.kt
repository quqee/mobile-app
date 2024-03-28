package com.suslanium.hackathon.createdefect.presentation.ui.screen

import android.widget.Toast
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.suslanium.hackathon.R
import com.suslanium.hackathon.createdefect.presentation.CreateDefectViewModel
import com.suslanium.hackathon.createdefect.presentation.ui.common.ObserveAsEvents
import com.suslanium.hackathon.createdefect.presentation.ui.screen.components.DefectTypeListContent
import com.suslanium.hackathon.createdefect.presentation.ui.screen.components.ErrorContent
import com.suslanium.hackathon.createdefect.presentation.ui.screen.components.GeneralInfoContent
import com.suslanium.hackathon.createdefect.presentation.ui.screen.components.LoadingContent
import com.suslanium.hackathon.createdefect.presentation.ui.state.CreateDefectScreenState
import com.suslanium.hackathon.createdefect.presentation.ui.state.CreateDefectSectionState
import com.suslanium.hackathon.createdefect.presentation.ui.state.DefectCreationEvent
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateDefectScreen(onNavigateAfterSuccess: () -> Unit) {
    val context = LocalContext.current
    val viewModel: CreateDefectViewModel = koinViewModel()
    val screenState by remember {
        viewModel.screenState
    }

    ObserveAsEvents(flow = viewModel.creationEvents) {
        when (it) {
            DefectCreationEvent.Error -> Toast.makeText(
                context, R.string.generic_error_msg, Toast.LENGTH_LONG
            ).show()

            DefectCreationEvent.Success -> onNavigateAfterSuccess()
        }
    }

    Crossfade(targetState = screenState, label = "") {
        when (it) {
            CreateDefectScreenState.Content -> CreateDefectContent(viewModel = viewModel)
            CreateDefectScreenState.Error -> ErrorContent(onRetry = viewModel::loadDefectTypes)
            CreateDefectScreenState.Loading -> LoadingContent()
        }
    }


}

@Composable
fun CreateDefectContent(viewModel: CreateDefectViewModel) {
    val section by remember { viewModel.sectionState }
    val defectTypes by remember { viewModel.defectTypes }

    Crossfade(targetState = section, label = "") {
        when (it) {
            CreateDefectSectionState.DefectTypeSelection -> DefectTypeListContent(
                defectTypes = defectTypes,
                onSelectType = viewModel::setDefectType,
                onBack = viewModel::openGeneralContent
            )

            CreateDefectSectionState.GeneralContent -> GeneralInfoContent(viewModel = viewModel)
        }
    }
}