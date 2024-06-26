package com.suslanium.hackathon.createdefect.data.repository

import android.content.Context
import android.net.Uri
import com.suslanium.hackathon.createdefect.data.api.CreateDefectApiService
import com.suslanium.hackathon.createdefect.data.model.CreateDefectModel
import com.suslanium.hackathon.createdefect.data.model.DefectType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class CreateDefectRepository(
    private val apiService: CreateDefectApiService, private val context: Context
) {

    suspend fun getDefectTypes(): List<DefectType> = apiService.getDefectTypes()

    suspend fun createRequest(defectModel: CreateDefectModel) {
        var requestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart("latitude", defectModel.latitude.toString())
            .addFormDataPart("longitude", defectModel.longitude.toString())
            .addFormDataPart("defect_type_id", defectModel.defectType.id)
            .addFormDataPart("statement_id", defectModel.statementId)

        val tempFiles = mutableListOf<File>()

        for (uri in defectModel.fileUris) {
            val file = uri.createTempFile()
            requestBody = requestBody.addFormDataPart(
                "photos", file.name, file.asRequestBody("image/*".toMediaTypeOrNull())
            )
            tempFiles.add(file)
        }

        if (defectModel.defectType.hasDistance && defectModel.defectDistance != null) requestBody =
            requestBody.addFormDataPart(
                "defect_distance", defectModel.defectDistance.toString()
            )

        apiService.createDefect(requestBody.build())
        for (file in tempFiles) {
            file.delete()
        }
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