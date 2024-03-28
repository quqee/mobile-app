package com.suslanium.hackathon.createdefect.presentation.ui.screen.components

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.suslanium.hackathon.R
import com.suslanium.hackathon.core.ui.theme.DarkBlue
import com.suslanium.hackathon.core.ui.theme.Error
import com.suslanium.hackathon.core.ui.theme.LightGray

fun LazyGridScope.editableDefectImageList(
    imageUris: SnapshotStateList<Uri>,
    addLauncher: ManagedActivityResultLauncher<String, List<Uri>>
) {
    items(imageUris.size) {
        Box(contentAlignment = Alignment.Center) {
            Image(
                modifier = Modifier
                    .defaultMinSize(
                        minWidth = 100.dp, minHeight = 100.dp
                    )
                    .aspectRatio(1f)
                    .padding(all = 5.dp)
                    .clip(RoundedCornerShape(10.dp)),
                painter = rememberAsyncImagePainter(model = imageUris[it]),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            IconButton(modifier = Modifier
                .size(24.dp)
                .align(Alignment.TopEnd),
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = DarkBlue, contentColor = Error
                ),
                onClick = { imageUris.removeAt(it) }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.close_icon),
                    contentDescription = null
                )
            }
        }
    }
    item {
        Box(contentAlignment = Alignment.Center) {
            Box(
                modifier = Modifier
                    .clickable { addLauncher.launch("image/*") }
                    .defaultMinSize(
                        minWidth = 100.dp, minHeight = 100.dp
                    )
                    .aspectRatio(1f)
                    .padding(all = 5.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(LightGray),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(48.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.add_photo_icon),
                    tint = DarkBlue,
                    contentDescription = null
                )
            }
        }
    }
}