package com.suslanium.hackathon.statements.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.suslanium.hackathon.core.ui.theme.PaddingSmall
import com.suslanium.hackathon.core.ui.theme.Primary
import com.suslanium.hackathon.core.ui.theme.S15_W600
import com.suslanium.hackathon.core.ui.theme.S16_W700
import com.suslanium.hackathon.createdefect.presentation.ui.screen.components.ErrorContent
import com.suslanium.hackathon.createdefect.presentation.ui.screen.components.LoadingContent
import com.suslanium.hackathon.statements.presentation.components.ShortDefectCard
import com.suslanium.hackathon.statements.presentation.state.StatementUiState
import com.suslanium.hackathon.statements.presentation.viewmodel.StatementViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun StatementScreen(
    statementId: String,
    onNavigateBack: () -> Unit,
    onNavigateToCreateDefect: (String) -> Unit,
    // TODO onNavigateToCreateOrder
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
                Column {
                    ExtendedFloatingActionButton(
                        text = {
                            Text(
                                text = stringResource(id = R.string.defect),
                                style = S15_W600
                            )
                        },
                        onClick = {
                            onNavigateToCreateDefect(statementId)
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

                    Spacer(modifier = Modifier.height(PaddingSmall))

                    ExtendedFloatingActionButton(
                        text = {
                            Text(
                                text = stringResource(id = R.string.order),
                                style = S15_W600
                            )
                        },
                        onClick = {
                            // TODO navigate to add an order
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
        }
    ) {
        when (uiState) {
            StatementUiState.Initial -> Unit
            StatementUiState.Loading -> LoadingContent()
            StatementUiState.Error -> ErrorContent(onRetry = viewModel::onRetry)
            is StatementUiState.Success -> {
                val statement = (uiState as StatementUiState.Success).statement
                Column(
                    modifier = modifier
                        .padding(it)
                        .padding(horizontal = PaddingMedium)
                        .background(Color.White),
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

                    if (statement.defects.isNotEmpty()) {
                        Text(
                            text = stringResource(id = R.string.defects),
                            style = S16_W700,
                            color = DarkBlue
                        )

                        LazyColumn {
                            items(statement.defects) { defect ->
                                ShortDefectCard(defect = defect)

                                Spacer(modifier = Modifier.height(20.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}