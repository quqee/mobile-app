package com.suslanium.hackathon.defect.data.model

import com.google.gson.annotations.SerializedName

data class DefectModel(
    @SerializedName("id") val defectId: String,
    val latitude: Double,
    val longitude: Double,
    @SerializedName("type") val defectType: String,
    @SerializedName("distance") val defectDistance: Double?,
    val status: DefectStatus,
    @SerializedName("pictures_before_repair") val picturesBeforeRepair: List<String>,
    @SerializedName("pictures_after_repair") val picturesAfterRepair: List<String>
)
