package com.suslanium.hackathon.statements.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.suslanium.hackathon.R
import com.suslanium.hackathon.core.localDateTimeToDate
import com.suslanium.hackathon.core.ui.common.StatementShortCard
import com.suslanium.hackathon.core.ui.theme.DarkBlue
import com.suslanium.hackathon.core.ui.theme.PaddingMedium
import com.suslanium.hackathon.core.ui.theme.PaddingRegular
import com.suslanium.hackathon.core.ui.theme.Primary
import com.suslanium.hackathon.core.ui.theme.S20_W700
import com.suslanium.hackathon.statements.data.model.StatementResponseDto

@Composable
fun StatementsContent(
    statements: List<StatementResponseDto>,
    onNavigateToStatement: (String) -> Unit,
    onNavigateToCreateStatement: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentPadding = PaddingValues(
                vertical = 20.dp,
                horizontal = PaddingMedium
            ),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.statementsRu),
                    style = S20_W700,
                    color = DarkBlue,
                    textAlign = TextAlign.Center
                )
            }

            items(statements) { statement ->
                StatementShortCard(
                    id = statement.statementId,
                    roadName = statement.areaName,
                    category = statement.roadType.description,
                    date = statement.createTime.localDateTimeToDate(),
                    distance = statement.length.toString(),
                    onNavigateToStatement = onNavigateToStatement
                )
            }
        }

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    bottom = PaddingMedium,
                    end = PaddingRegular
                ),
            onClick = onNavigateToCreateStatement,
            containerColor = Primary,
            contentColor = Color.White
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_plus),
                contentDescription = stringResource(id = R.string.createStatement),
            )
        }
    }
}