package com.suslanium.hackathon.createdefect.presentation.ui.screen.components

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
import com.suslanium.hackathon.createdefect.data.model.DefectType

@Composable
fun DefectTypeListContent(
    defectTypes: List<DefectType>, onSelectType: (DefectType) -> Unit = {}, onBack: () -> Unit = {}
) {
    BackHandler {
        onBack()
    }

    Column(modifier = Modifier.padding(all = 16.dp)) {
        IconButton(
            modifier = Modifier
                .align(Alignment.Start)
                .size(24.dp), onClick = onBack
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
            text = stringResource(id = R.string.choose_defect_type),
            style = S20_W700,
            color = DarkBlue,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn {
            items(defectTypes.size, key = { defectTypes[it].id }) {
                val defectType = defectTypes[it]
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onSelectType(defectType)
                        onBack()
                    }) {
                    Text(
                        modifier = Modifier.padding(vertical = 10.dp),
                        text = defectType.name,
                        style = S15_W500,
                        color = LightGray
                    )
                    Divider(color = DividerColor)
                }
            }
        }
    }
}