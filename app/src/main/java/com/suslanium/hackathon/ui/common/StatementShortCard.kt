package com.suslanium.hackathon.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suslanium.hackathon.ui.theme.BlueGray
import com.suslanium.hackathon.ui.theme.LightGray
import com.suslanium.hackathon.ui.theme.S14_W400
import com.suslanium.hackathon.ui.theme.S16_W700

@Composable
fun StatementShortCard(
    modifier: Modifier, roadName: String, category: String, date: String, distance: String
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = roadName,
                    style = S16_W700,
                )
                Spacer(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.padding(top = 8.dp))
            Text(
                text = category, style = S14_W400, color = BlueGray
            )
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 20.dp),
                thickness = 1.dp,
                color = LightGray
            )
            Row {
                Icon(
                    Icons.Default.CalendarMonth,
                    contentDescription = null,
                    tint = BlueGray,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = "до $date",
                    style = S14_W400,
                    color = BlueGray,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Spacer(modifier = Modifier.padding(top = 8.dp))
            Row {
                Icon(
                    Icons.Default.Navigation,
                    contentDescription = null,
                    tint = BlueGray,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = distance,
                    style = S14_W400,
                    color = BlueGray,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardPreview() {
    Column {
        StatementShortCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { },
            roadName = "Московское шоссе",
            category = "Автомобильная дорога",
            date = "12.12.2021",
            distance = "20 км"
        )
        StatementShortCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { },
            roadName = "Московское шоссе",
            category = "Автомобильная дорога",
            date = "12.12.2021",
            distance = "20 км"
        )
        StatementShortCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { },
            roadName = "Московское шоссе",
            category = "Автомобильная дорога",
            date = "12.12.2021",
            distance = "20 км"
        )
    }
}

