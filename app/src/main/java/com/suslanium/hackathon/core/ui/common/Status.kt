package com.suslanium.hackathon.core.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CropFree
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.IncompleteCircle
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suslanium.hackathon.R
import com.suslanium.hackathon.core.ui.theme.Error
import com.suslanium.hackathon.core.ui.theme.Green
import com.suslanium.hackathon.core.ui.theme.LightGray
import com.suslanium.hackathon.core.ui.theme.Primary
import com.suslanium.hackathon.core.ui.theme.S12_W400
import com.suslanium.hackathon.core.ui.theme.Yellow

@Composable
fun StatusElement(modifier: Modifier = Modifier, status: Status) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        when (status) {
            Status.DONE -> {
                Icon(imageVector = Icons.Default.Done, contentDescription = null, tint = Green)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = stringResource(R.string.fixed), color = Green)
            }

            Status.IN_PROGRESS -> {
                Icon(imageVector = Icons.Default.Timeline, contentDescription = null, tint = Yellow)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = stringResource(R.string.in_progress), color = Yellow)
            }

            Status.WAITING -> {
                Icon(
                    imageVector = Icons.Default.IncompleteCircle,
                    contentDescription = null,
                    tint = Primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = stringResource(R.string.waiting), color = Primary)
            }

            Status.CANCELLED -> {

                Icon(imageVector = Icons.Default.Cancel, contentDescription = null, tint = Error)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = stringResource(R.string.cancelled), style = S12_W400, color = Error)
            }

            Status.OPENED -> {
                Icon(
                    imageVector = Icons.Default.CropFree,
                    contentDescription = null,
                    tint = LightGray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = stringResource(R.string.opened), style = S12_W400, color = LightGray)
            }
        }
    }
}

enum class Status {
    DONE, IN_PROGRESS, CANCELLED, WAITING, OPENED
}

@Preview
@Composable
private fun StatusPreview() {
    Column {
        StatusElement(status = Status.DONE)
        StatusElement(status = Status.WAITING)
        StatusElement(status = Status.IN_PROGRESS)
        StatusElement(status = Status.CANCELLED)
        StatusElement(status = Status.OPENED)
    }
}