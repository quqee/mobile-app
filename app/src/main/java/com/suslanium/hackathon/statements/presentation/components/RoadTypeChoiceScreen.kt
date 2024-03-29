package com.suslanium.hackathon.statements.presentation.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.suslanium.hackathon.R
import com.suslanium.hackathon.core.ui.theme.DarkBlue
import com.suslanium.hackathon.core.ui.theme.DividerColor
import com.suslanium.hackathon.core.ui.theme.LightGray
import com.suslanium.hackathon.core.ui.theme.S15_W500
import com.suslanium.hackathon.core.ui.theme.S20_W700
import com.suslanium.hackathon.statements.data.model.RoadType
import androidx.compose.foundation.lazy.items
import com.suslanium.hackathon.statements.presentation.viewmodel.CreateStatementViewModel

@Composable
fun RoadTypeChoiceScreen(
    onNavigateBack: () -> Unit,
    viewModel: CreateStatementViewModel
) {
    BackHandler {
        onNavigateBack()
    }

    Column(modifier = Modifier.padding(all = 16.dp)) {
        IconButton(
            modifier = Modifier
                .align(Alignment.Start)
                .size(24.dp), onClick = onNavigateBack
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.chevron_left),
                contentDescription = null,
                tint = DarkBlue
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.chooseRoadType),
            style = S20_W700,
            color = DarkBlue,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn {
            items(RoadType.entries) { roadType ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.onRoadTypeChange(roadType)
                            onNavigateBack()
                        }
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 10.dp),
                        text = roadType.description,
                        style = S15_W500,
                        color = LightGray
                    )
                    Divider(color = DividerColor)
                }
            }
        }
    }
}