package com.suslanium.hackathon.defect.data.repository

import android.content.Context
import android.net.Uri
import com.suslanium.hackathon.defect.data.api.DefectApiService
import com.suslanium.hackathon.defect.data.model.DefectModel
import com.suslanium.hackathon.defect.data.model.DefectStatus
import com.suslanium.hackathon.defect.data.model.UpdateStatusModel
import kotlinx.coroutines.delay
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

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
            val file = uri.createTempFile()
            val requestBody = MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart(
                "file", file.name, file.asRequestBody("image/*".toMediaTypeOrNull())
            ).build()
            defectApiService.addReviewPicture(defectId, requestBody)
            file.delete()
        }
    }

    suspend fun rejectDefect(defectId: String) {
        delay(1000)
    }

    suspend fun returnDefect(defectId: String) {
        delay(1000)
    }

    private fun Uri.createTempFile(): File {
        val fileStream = context.contentResolver.openInputStream(this)
        val fileBytes = fileStream?.readBytes()
        fileStream?.close()

        val outputDir: File = context.cacheDir
        val outputFile = File.createTempFile("prefix", "-suffix", outputDir)
        if (fileBytes != null) {
            outputFile.writeBytes(fileBytes)
        }
        return outputFile
    }

}