package com.suslanium.hackathon.statements.presentation.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.suslanium.hackathon.core.ui.theme.DarkBlue
import com.suslanium.hackathon.core.ui.theme.S20_W700

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatementsTopBar(
    text: String,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = text,
                style = S20_W700,
                color = DarkBlue
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            navigationIconContentColor = DarkBlue,
            titleContentColor = DarkBlue
        )
    )
}