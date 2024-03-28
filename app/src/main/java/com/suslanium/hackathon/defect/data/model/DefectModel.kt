package com.suslanium.hackathon.defect.data.model

data class DefectModel(
    val defectId: String,
    val latitude: Double,
    val longitude: Double,
    val defectType: String,
    val defectDistance: Double?,
    val status: DefectStatus,
    val picturesBeforeRepair: List<String>,
    val picturesAfterRepair: List<String>
)
