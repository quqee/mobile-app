package com.suslanium.hackathon.defect.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import com.yandex.mapkit.map.CameraPosition

@Composable
fun MapView(
    modifier: Modifier = Modifier, blockInput: Boolean = true, startCameraPosition: CameraPosition
) {
    val lifeCycleState by LocalLifecycleOwner.current.lifecycle.observeAsState()

    AndroidView(modifier = modifier, factory = {
        val mapView = com.yandex.mapkit.mapview.MapView(it)
        mapView.mapWindow.map.move(startCameraPosition)
        if (blockInput) {
            mapView.mapWindow.map.isTiltGesturesEnabled = false
            mapView.mapWindow.map.isRotateGesturesEnabled = false
            mapView.mapWindow.map.isScrollGesturesEnabled = false
            mapView.mapWindow.map.isZoomGesturesEnabled = false
        }
        return@AndroidView mapView
    }, update = {
        when (lifeCycleState) {
            Lifecycle.Event.ON_RESUME -> it.onStart()
            Lifecycle.Event.ON_PAUSE -> it.onStop()
            else -> Unit
        }
    })
}