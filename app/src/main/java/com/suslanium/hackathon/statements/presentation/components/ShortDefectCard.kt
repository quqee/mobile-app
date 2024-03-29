package com.suslanium.hackathon.statements.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suslanium.hackathon.core.ui.theme.BlueGray
import com.suslanium.hackathon.core.ui.theme.DarkBlue
import com.suslanium.hackathon.core.ui.theme.PaddingSmall
import com.suslanium.hackathon.core.ui.theme.S11_W500
import com.suslanium.hackathon.core.ui.theme.S14_W400
import com.suslanium.hackathon.core.ui.theme.S16_W700
import com.suslanium.hackathon.statements.data.model.ShortDefectDto
import com.suslanium.hackathon.statements.data.model.StatementStatus

@Composable
fun ShortDefectCard(
    defect: ShortDefectDto,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, top = 20.dp, end = 16.dp, bottom = 20.dp)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = defect.status.iconId),
                    contentDescription = null,
                    tint = defect.status.color
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = defect.status.description,
                    style = S11_W500,
                    color = defect.status.color
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = defect.type,
                style = S16_W700,
                color = DarkBlue
            )

            Spacer(modifier = Modifier.height(PaddingSmall))

            Text(
                text = defect.description ?: "",
                style = S14_W400,
                color = BlueGray
            )
        }
    }
}

@Preview
@Composable
private fun Prev() {
    ShortDefectCard(
        defect = ShortDefectDto(
            id = "123123",
            status = StatementStatus.IN_PROCESS,
            type = "Беды с разметкой",
            description = "Вожовский переулок, Кромы, Стрелецкое сельское поселение, Кромской район, Орловская область, Центральный федеральный округ, 303210, Россия"
        )
    )
}