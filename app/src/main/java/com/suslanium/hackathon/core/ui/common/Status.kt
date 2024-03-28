package com.suslanium.hackathon.core.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.IncompleteCircle
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suslanium.hackathon.R
import com.suslanium.hackathon.core.ui.theme.Error
import com.suslanium.hackathon.core.ui.theme.Green
import com.suslanium.hackathon.core.ui.theme.Primary
import com.suslanium.hackathon.core.ui.theme.S12_W400
import com.suslanium.hackathon.core.ui.theme.Yellow

@Composable
fun StatusElement(status: Status) {
    when (status) {
        Status.DONE -> {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Done, contentDescription = null, tint = Green)
                Spacer(modifier = androidx.compose.ui.Modifier.width(8.dp))
                Text(text = stringResource(R.string.fixed), color = Green)
            }
        }

        Status.IN_PROGRESS -> {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Timeline, contentDescription = null, tint = Yellow)
                Spacer(modifier = androidx.compose.ui.Modifier.width(8.dp))
                Text(text = stringResource(R.string.in_progress), color = Yellow)
            }
        }

        Status.WAITING -> {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.IncompleteCircle,
                    contentDescription = null,
                    tint = Primary
                )
                Spacer(modifier = androidx.compose.ui.Modifier.width(8.dp))
                Text(text = stringResource(R.string.waiting), color = Primary)
            }
        }

        Status.CANCELLED -> {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Cancel, contentDescription = null, tint = Error)
                Spacer(modifier = androidx.compose.ui.Modifier.width(8.dp))
                Text(text = stringResource(R.string.cancelled), style = S12_W400, color = Error)
            }
        }
    }

}

enum class Status {
    DONE,
    IN_PROGRESS,
    CANCELLED,
    WAITING,
}

@Preview
@Composable
private fun StatusPreview() {
    Column {
        StatusElement(Status.DONE)
        StatusElement(Status.IN_PROGRESS)
        StatusElement(Status.WAITING)
        StatusElement(Status.CANCELLED)
    }
}