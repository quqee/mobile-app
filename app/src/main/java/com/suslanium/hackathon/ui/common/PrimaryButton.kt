package com.suslanium.hackathon.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.suslanium.hackathon.ui.theme.Primary
import com.suslanium.hackathon.ui.theme.PrimaryDisabled
import com.suslanium.hackathon.ui.theme.S15_W600

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    Button(modifier = modifier
        .height(42.dp)
        .fillMaxWidth(), shape = RoundedCornerShape(10.dp), colors = ButtonDefaults.buttonColors(
        containerColor = Primary,
        contentColor = Color.White,
        disabledContainerColor = PrimaryDisabled,
        disabledContentColor = Color.White
    ), onClick = onClick, enabled = enabled) {
        Text(text = text, style = S15_W600)
    }
}