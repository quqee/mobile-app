package com.suslanium.hackathon.core.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.suslanium.hackathon.core.ui.theme.Primary

@Composable
fun LoadingPrimaryButton(modifier: Modifier = Modifier) {
    Button(modifier = modifier
        .height(42.dp)
        .fillMaxWidth(), shape = RoundedCornerShape(10.dp), colors = ButtonDefaults.buttonColors(
        containerColor = Primary,
        contentColor = Color.White,
        disabledContainerColor = Primary,
        disabledContentColor = Color.White
    ), onClick = {}, enabled = false) {
        CircularProgressIndicator(
            modifier = Modifier.size(24.dp),
            color = Color.White
        )
    }
}