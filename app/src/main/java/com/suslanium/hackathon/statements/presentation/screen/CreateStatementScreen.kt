package com.suslanium.hackathon.statements.presentation.screen

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.suslanium.hackathon.statements.presentation.components.CreateStatementForm
import com.suslanium.hackathon.statements.presentation.components.RoadTypeChoiceScreen
import com.suslanium.hackathon.statements.presentation.components.SurfaceTypeChoiceScreen
import com.suslanium.hackathon.statements.presentation.state.CreateStatementSection
import com.suslanium.hackathon.statements.presentation.viewmodel.CreateStatementViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateStatementScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CreateStatementViewModel = koinViewModel()
) {
    val section by viewModel.createStatementSection.collectAsStateWithLifecycle()

    Crossfade(
        targetState = section,
        label = ""
    ) {
        when (it) {
            CreateStatementSection.CreationForm -> CreateStatementForm(
                modifier = modifier,
                onNavigateBack = onNavigateBack,
                viewModel = viewModel
            )
            CreateStatementSection.RoadTypes -> RoadTypeChoiceScreen(
                onNavigateBack = viewModel::openCreationForm,
                viewModel = viewModel
            )
            CreateStatementSection.SurfaceTypes -> SurfaceTypeChoiceScreen(
                onNavigateBack = viewModel::openCreationForm,
                viewModel = viewModel
            )
        }
    }
}