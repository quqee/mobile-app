package com.suslanium.hackathon.statements.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.suslanium.hackathon.R
import com.suslanium.hackathon.core.localDateTimeToDate
import com.suslanium.hackathon.core.ui.common.RoadCareTopBar
import com.suslanium.hackathon.core.ui.common.StatementFullCard
import com.suslanium.hackathon.core.ui.theme.DarkBlue
import com.suslanium.hackathon.core.ui.theme.PaddingMedium
import com.suslanium.hackathon.core.ui.theme.Primary
import com.suslanium.hackathon.core.ui.theme.S15_W600
import com.suslanium.hackathon.core.ui.theme.S16_W700
import com.suslanium.hackathon.statements.presentation.components.ShortDefectCard
import com.suslanium.hackathon.statements.presentation.state.StatementUiState
import com.suslanium.hackathon.statements.presentation.viewmodel.StatementViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun StatementScreen(
    statementId: String,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: StatementViewModel = koinViewModel(parameters = { parametersOf(statementId) })
    val uiState by viewModel.statementUiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            RoadCareTopBar(
                text = stringResource(id = R.string.statement),
                onBackButtonClick = onNavigateBack
            )
        },
        floatingActionButton = {
            if (uiState is StatementUiState.Success) {
                // TODO two FABs
                ExtendedFloatingActionButton(
                    text = {
                        Text(
                            text = stringResource(id = R.string.defect),
                            style = S15_W600
                        )
                    },
                    onClick = {
                        // TODO navigate to add a defect or add an order
                    },
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_plus),
                            contentDescription = stringResource(id = R.string.createStatement),
                        )
                    },
                    containerColor = Primary,
                    contentColor = Color.White
                )
            }
        }
    ) {
        when (uiState) {
            StatementUiState.Initial -> Unit
            StatementUiState.Loading -> Unit
            StatementUiState.Error -> TODO()
            is StatementUiState.Success -> {
                val statement = (uiState as StatementUiState.Success).statement
                Column(
                    modifier = modifier
                        .padding(it)
                        .padding(horizontal = PaddingMedium)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    StatementFullCard(
                        roadName = statement.areaName,
                        category = statement.roadType.description,
                        date = statement.createTime.localDateTimeToDate(),
                        length = statement.length.toString(),
                        roadType = statement.roadType.description,
                        direction = statement.direction
                    )

                    Text(
                        text = stringResource(id = R.string.defects),
                        style = S16_W700,
                        color = DarkBlue
                    )

                    LazyColumn(
                        contentPadding = PaddingValues(bottom = 20.dp)
                    ) {
                        items(statement.defects) { defect ->
                            ShortDefectCard(defect = defect)
                        }
                    }
                }
            }
        }
    }
}