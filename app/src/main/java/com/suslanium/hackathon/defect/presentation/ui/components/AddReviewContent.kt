package com.suslanium.hackathon.defect.presentation.ui.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.suslanium.hackathon.R
import com.suslanium.hackathon.core.ui.common.PrimaryButton
import com.suslanium.hackathon.core.ui.theme.DarkBlue
import com.suslanium.hackathon.core.ui.theme.S20_W700
import com.suslanium.hackathon.createdefect.presentation.ui.screen.components.editableDefectImageList


@Composable
fun AddReviewContent(
    onBack: () -> Unit, imageUris: SnapshotStateList<Uri>, onUploadClick: () -> Unit
) {
    val replaceLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult = { uri: List<Uri> ->
            imageUris.clear()
            imageUris.addAll(uri)
        })
    val addLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult = { uri: List<Uri> ->
            imageUris.addAll(uri)
        })

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
            text = stringResource(id = R.string.work_result),
            style = S20_W700,
            color = DarkBlue,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))

        if (imageUris.isEmpty()) {
            PrimaryButton(text = stringResource(id = R.string.upload_photo),
                onClick = { replaceLauncher.launch("image/*") })
        } else {
            LazyVerticalGrid(modifier = Modifier.weight(1f), columns = GridCells.Adaptive(110.dp)) {
                editableDefectImageList(imageUris, addLauncher)
            }

            PrimaryButton(text = stringResource(id = R.string.mark_as_done), onClick = { })
        }
    }
}