package com.suslanium.hackathon.statements.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.suslanium.hackathon.R
import com.suslanium.hackathon.core.ui.common.AppOutlinedTextField
import com.suslanium.hackathon.core.ui.common.LoadingPrimaryButton
import com.suslanium.hackathon.core.ui.common.PrimaryButton
import com.suslanium.hackathon.core.ui.common.RoadCareTopBar
import com.suslanium.hackathon.core.ui.theme.Error
import com.suslanium.hackathon.core.ui.theme.PaddingMedium
import com.suslanium.hackathon.core.ui.theme.S12_W400
import com.suslanium.hackathon.statements.presentation.state.CreateStatementUiState
import com.suslanium.hackathon.statements.presentation.viewmodel.CreateStatementViewModel

@Composable
fun CreateStatementForm(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CreateStatementViewModel
) {
    val uiState by viewModel.createStatementUiState.collectAsStateWithLifecycle()
    val createStatementState by viewModel.createStatementState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState) {
        if (uiState is CreateStatementUiState.Success) {
            onNavigateBack()
        }
    }

    Scaffold(
        topBar = {
            RoadCareTopBar(
                text = stringResource(id = R.string.newStatement),
                onBackButtonClick = onNavigateBack
            )
        },
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = PaddingMedium)
                .background(Color.White),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            AppOutlinedTextField(
                value = createStatementState.areaName,
                onValueChange = viewModel::onAreaNameChange,
                label = stringResource(id = R.string.roadName)
            )

            AppOutlinedTextField(
                value = createStatementState.roadLength,
                onValueChange = viewModel::onRoadLengthChange,
                label = stringResource(id = R.string.roadLength)
            )

            AppOutlinedTextField(
                modifier = Modifier.pointerInput(Unit) {
                    detectTapGestures {
                        viewModel.openRoadTypeSelection()
                    }
                },
                value = createStatementState.roadType?.description ?: "",
                onValueChange = {},
                enabled = false,
                label = stringResource(id = R.string.roadType),
                trailingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.expand_icon),
                        contentDescription = null
                    )
                }
            )

            AppOutlinedTextField(
                modifier = Modifier.pointerInput(Unit) {
                    detectTapGestures {
                        viewModel.openSurfaceTypeSelection()
                    }
                },
                value = createStatementState.surfaceType?.description ?: "",
                onValueChange = {},
                enabled = false,
                label = stringResource(id = R.string.roadSurface),
                trailingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.expand_icon),
                        contentDescription = null
                    )
                }
            )

            AppOutlinedTextField(
                value = createStatementState.direction,
                onValueChange = viewModel::onDirectionChange,
                label = stringResource(id = R.string.direction)
            )

            if (uiState !is CreateStatementUiState.Loading) {
                PrimaryButton(
                    text = stringResource(id = R.string.create),
                    enabled = createStatementState.areFieldsFilled,
                    onClick = {
                        viewModel.onCreateStatement()
                    }
                )
            } else {
                LoadingPrimaryButton()
            }

            if (uiState is CreateStatementUiState.Error) {
                Text(
                    text = stringResource(id = R.string.statementCreationError),
                    style = S12_W400,
                    color = Error
                )
            }
        }
    }
}