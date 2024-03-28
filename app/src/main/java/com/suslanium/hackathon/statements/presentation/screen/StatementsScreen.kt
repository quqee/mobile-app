package com.suslanium.hackathon.statements.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.suslanium.hackathon.statements.presentation.state.StatementsUiState
import com.suslanium.hackathon.statements.presentation.viewmodel.StatementsViewModel
import com.suslanium.hackathon.statements.presentation.components.StatementsContent
import org.koin.androidx.compose.koinViewModel

@Composable
fun StatementsScreen(
    modifier: Modifier = Modifier,
    viewModel: StatementsViewModel = koinViewModel()
) {
    val uiState by viewModel.statementsUiState.collectAsStateWithLifecycle()

    when (uiState) {
        StatementsUiState.Initial -> Unit
        StatementsUiState.Loading -> TODO()
        StatementsUiState.Error -> TODO()
        is StatementsUiState.Success -> {
            val statements = (uiState as StatementsUiState.Success).statements
            StatementsContent(
                modifier = modifier,
                statements = statements
            )
        }
    }


}