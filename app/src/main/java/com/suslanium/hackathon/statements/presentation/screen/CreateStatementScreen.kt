package com.suslanium.hackathon.statements.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.suslanium.hackathon.R
import com.suslanium.hackathon.core.ui.common.AppOutlinedTextField
import com.suslanium.hackathon.core.ui.common.PrimaryButton
import com.suslanium.hackathon.core.ui.common.RoadCareTopBar
import com.suslanium.hackathon.core.ui.theme.PaddingMedium
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateStatementScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
//    viewModel: CreateStatementViewModel = koinViewModel()
) {
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
                value = "",
                onValueChange = {},
                label = stringResource(id = R.string.roadName)
            )

            AppOutlinedTextField(
                value = "",
                onValueChange = {},
                label = stringResource(id = R.string.roadLength)
            )

            AppOutlinedTextField(
                value = "",
                onValueChange = {},
                label = stringResource(id = R.string.roadType)
            )

            AppOutlinedTextField(
                value = "",
                onValueChange = {},
                label = stringResource(id = R.string.roadSurface)
            )

            AppOutlinedTextField(
                value = "",
                onValueChange = {},
                label = stringResource(id = R.string.direction)
            )

            PrimaryButton(
                text = stringResource(id = R.string.create),
                onClick = {
                    // TODO
                }
            )
        }
    }
}