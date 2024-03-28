package com.suslanium.hackathon.core.ui.common

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.suslanium.hackathon.R
import com.suslanium.hackathon.core.ui.theme.DarkBlue
import com.suslanium.hackathon.core.ui.theme.S20_W700

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoadCareTopBar(
    text: String,
    onBackButtonClick: () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = text,
                style = S20_W700,
                color = DarkBlue
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onBackButtonClick
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_left),
                    contentDescription = stringResource(id = R.string.goBack),
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            navigationIconContentColor = DarkBlue,
            titleContentColor = DarkBlue
        )
    )
}