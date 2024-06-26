package com.suslanium.hackathon.core.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.CropFree
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.IncompleteCircle
import androidx.compose.material.icons.outlined.Timeline
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
                Icon(imageVector = Icons.Outlined.Done, contentDescription = null, tint = Green)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = stringResource(R.string.fixed), color = Green)
            }

            Status.IN_PROGRESS -> {
                Icon(imageVector = Icons.Outlined.Timeline, contentDescription = null, tint = Yellow)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = stringResource(R.string.in_progress), color = Yellow)
            }

            Status.WAITING -> {
                Icon(
                    imageVector = Icons.Outlined.IncompleteCircle,
                    contentDescription = null,
                    tint = Primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = stringResource(R.string.waiting), color = Primary)
            }

            Status.CANCELLED -> {

                Icon(imageVector = Icons.Outlined.Cancel, contentDescription = null, tint = Error)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = stringResource(R.string.cancelled), style = S12_W400, color = Error)
            }

            Status.OPENED -> {
                Icon(imageVector = Icons.Outlined.CropFree, contentDescription = null, tint = LightGray)
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