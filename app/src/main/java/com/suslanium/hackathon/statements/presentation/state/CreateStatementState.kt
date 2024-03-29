package com.suslanium.hackathon.statements.presentation.state

import com.suslanium.hackathon.statements.data.model.RoadType
import com.suslanium.hackathon.statements.data.model.SurfaceType

data class CreateStatementState(
    val areaName: String = "",
    val roadLength: String = "",
    val roadType: RoadType? = null,
    val surfaceType: SurfaceType? = null,
    val direction: String = ""
) {
    val areFieldsFilled =
        areaName.isNotBlank() &&
        roadLength.isNotBlank() &&
        roadType != null &&
        surfaceType != null &&
        direction.isNotBlank()
}
