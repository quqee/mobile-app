package com.suslanium.hackathon.defect.data

data class DefectModel(
    val latitude: Double,
    val longitude: Double,
    val defectType: String,
    val defectDistance: Double?,
    val status: DefectStatus,
    val picturesBeforeRepair: List<String>,
    val picturesAfterRepair: List<String>
)
