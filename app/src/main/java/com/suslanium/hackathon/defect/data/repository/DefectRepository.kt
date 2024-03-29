package com.suslanium.hackathon.defect.data.repository

import android.content.Context
import android.net.Uri
import com.suslanium.hackathon.defect.data.api.DefectApiService
import com.suslanium.hackathon.defect.data.model.DefectModel
import com.suslanium.hackathon.defect.data.model.DefectStatus
import com.suslanium.hackathon.defect.data.model.UpdateStatusModel
import kotlinx.coroutines.delay
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

class DefectRepository(
    private val defectApiService: DefectApiService, private val context: Context
) {

    suspend fun getDefectInfo(defectId: String): DefectModel =
        defectApiService.getDefectInfo(defectId)

    suspend fun markDefectAsCompleted(defectId: String, photos: List<Uri>) {
        defectApiService.changeDefectStatus(
            defectId, UpdateStatusModel(status = DefectStatus.COMPLETED)
        )
        for (uri in photos) {
            val requestBody =
                MultipartBody.Builder().setType(MultipartBody.FORM).addFile(uri).build()
            defectApiService.addReviewPicture(defectId, requestBody)
        }
    }

    suspend fun rejectDefect(defectId: String) {
        delay(1000)
    }

    suspend fun returnDefect(defectId: String) {
        delay(1000)
    }

    private fun MultipartBody.Builder.addFile(
        uri: Uri, key: String = "file"
    ): MultipartBody.Builder {
        val fileStream = context.contentResolver.openInputStream(uri)
        val fileBytes = fileStream?.readBytes()
        fileStream?.close()
        return this.addFormDataPart(key, uri.lastPathSegment, fileBytes!!.toRequestBody())
    }

}