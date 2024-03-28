package com.suslanium.hackathon.core.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suslanium.hackathon.core.ui.theme.BlueGray
import com.suslanium.hackathon.core.ui.theme.S14_W400
import com.suslanium.hackathon.core.ui.theme.S16_W700

@Composable
fun ShortCard(
    modifier: Modifier = Modifier, status: Status? = null, title: String, desc: String
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ), colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ), modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, top = 20.dp, end = 16.dp, bottom = 20.dp)
                .fillMaxWidth()
        ) {
            if (status != null) StatusElement(status = status)
            Spacer(modifier = Modifier.padding(top = 8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = title,
                    style = S16_W700,
                )
                Spacer(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.padding(top = 8.dp))
            Text(
                text = desc, style = S14_W400, color = BlueGray
            )
        }
    }
}

@Preview
@Composable
private fun DefectShortCardPreview() {
    ShortCard(
        modifier = Modifier,
        status = Status.DONE,
        title = "Комната 101",
        desc = "Корпус 1"
    )
}