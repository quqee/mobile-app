package com.suslanium.hackathon.createdefect.data.model

import android.net.Uri

data class CreateDefectModel(
    val statementId: String,
    val latitude: Double,
    val longitude: Double,
    val defectType: DefectType,
    val defectDistance: Double?,
    val fileUris: List<Uri>
)
