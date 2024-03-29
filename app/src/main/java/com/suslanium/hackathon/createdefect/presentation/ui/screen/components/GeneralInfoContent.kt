package com.suslanium.hackathon.createdefect.presentation.ui.screen.components

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.suslanium.hackathon.R
import com.suslanium.hackathon.core.ui.common.AppOutlinedTextField
import com.suslanium.hackathon.core.ui.common.PrimaryButton
import com.suslanium.hackathon.core.ui.theme.DarkBlue
import com.suslanium.hackathon.core.ui.theme.S20_W700
import com.suslanium.hackathon.createdefect.presentation.CreateDefectViewModel
import com.suslanium.hackathon.createdefect.presentation.ui.MapSelectionActivity

@Composable
fun GeneralInfoContent(viewModel: CreateDefectViewModel, onBack: () -> Unit = {}) {
    val context = LocalContext.current
    val imageUris = remember { viewModel.fileUris }
    val latitude by remember {
        viewModel.latitude
    }
    val longitude by remember {
        viewModel.longitude
    }
    val defectType by remember {
        viewModel.defectType
    }
    val dataIsCorrectlyFilled by remember {
        viewModel.dataIsCorrectlyFilledIn
    }
    val defectDistance by remember {
        viewModel.defectDistance
    }
    val defectDistanceIsCorrectlyFilled by remember {
        viewModel.distanceIsCorrectlyFilled
    }

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
    val coordinatesLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode != Activity.RESULT_OK) return@rememberLauncherForActivityResult
            viewModel.setCoordinates(
                result.data?.extras?.getDouble(MapSelectionActivity.LATITUDE),
                result.data?.extras?.getDouble(MapSelectionActivity.LONGITUDE)
            )

        }

    Column(modifier = Modifier.fillMaxSize().background(Color.White).padding(all = 16.dp)) {
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
            text = stringResource(id = R.string.new_defect),
            style = S20_W700,
            color = DarkBlue,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        AppOutlinedTextField(modifier = Modifier.pointerInput(Unit) {
            detectTapGestures {
                viewModel.openTypeSelection()
            }
        },
            value = defectType?.name ?: "",
            onValueChange = {},
            enabled = false,
            label = stringResource(id = R.string.defect_type),
            trailingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.expand_icon),
                    contentDescription = null
                )
            })
        Spacer(modifier = Modifier.height(20.dp))
        AppOutlinedTextField(modifier = Modifier.pointerInput(Unit) {
            detectTapGestures {
                coordinatesLauncher.launch(Intent(context, MapSelectionActivity::class.java))
            }
        },
            enabled = false,
            value = if (latitude != null && longitude != null) "$latitude $longitude" else "",
            onValueChange = {},
            label = stringResource(id = R.string.coordinates),
            trailingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.location_icon),
                    contentDescription = null
                )
            })

        if (defectType != null && defectType?.hasDistance == true) {
            Spacer(modifier = Modifier.height(20.dp))
            AppOutlinedTextField(
                value = defectDistance,
                onValueChange = viewModel::setDefectDistance,
                label = stringResource(id = R.string.volume),
                isError = !defectDistanceIsCorrectlyFilled
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        if (imageUris.isEmpty()) {
            PrimaryButton(text = stringResource(id = R.string.upload_photo),
                onClick = { replaceLauncher.launch("image/*") })
        } else {
            LazyVerticalGrid(modifier = Modifier.weight(1f), columns = GridCells.Adaptive(110.dp)) {
                editableDefectImageList(imageUris, addLauncher)
            }

            PrimaryButton(
                text = stringResource(id = R.string.create),
                enabled = dataIsCorrectlyFilled,
                onClick = viewModel::createDefect
            )
        }
    }
}