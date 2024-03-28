package com.suslanium.hackathon.core.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suslanium.hackathon.R
import com.suslanium.hackathon.core.ui.theme.BlueGray
import com.suslanium.hackathon.core.ui.theme.LightGray
import com.suslanium.hackathon.core.ui.theme.S14_W400
import com.suslanium.hackathon.core.ui.theme.S16_W700

@Composable
fun StatementFullCard(
    modifier: Modifier,
    roadName: String,
    category: String,
    date: String,
    length: String,
    roadType: String,
    direction: String
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
                Text(
                    text = stringResource(R.string.creation_date), style = S14_W400
                )
                Text(
                    text = date,
                    style = S14_W400,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
            Spacer(modifier = Modifier.padding(top = 8.dp))
            Row {
                Text(
                    text = stringResource(R.string.road_length), style = S14_W400
                )
                Text(
                    text = length,
                    style = S14_W400,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
            Spacer(modifier = Modifier.padding(top = 8.dp))
            Row {
                Text(
                    text = stringResource(R.string.road_type), style = S14_W400
                )
                Text(
                    text = roadType,
                    style = S14_W400,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
            Spacer(modifier = Modifier.padding(top = 8.dp))
            Row {
                Text(
                    text = stringResource(R.string.road_direction), style = S14_W400
                )
                Text(
                    text = direction,
                    style = S14_W400,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FullCardPreview() {
    Column {
        StatementFullCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { },
            roadName = "Московское шоссе",
            category = "Автомобильная дорога",
            date = "12.12.2021",
            length = "Длина дороги",
            roadType = "Тип дороги",
            direction = "Направление"
        )
        StatementFullCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { },
            roadName = "Московское шоссе",
            category = "Автомобильная дорога",
            date = "12.12.2021",
            length = "Длина дороги",
            roadType = "Тип дороги",
            direction = "Направление"
        )
        StatementFullCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { },
            roadName = "Московское шоссе",
            category = "Автомобильная дорога",
            date = "12.12.2021",
            length = "Длина дороги",
            roadType = "Тип дороги",
            direction = "Направление"
        )
    }
}