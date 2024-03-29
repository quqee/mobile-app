package com.suslanium.hackathon.defect.data.api

import com.suslanium.hackathon.defect.data.model.DefectModel
import com.suslanium.hackathon.defect.data.model.UpdateStatusModel
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface DefectApiService {

    @GET("defects/{defectId}")
    suspend fun getDefectInfo(@Path("defectId") id: String): DefectModel

    @POST("defects/{defectId}/review")
    suspend fun addReviewPicture(@Path("defectId") defectId: String, @Body file: RequestBody)

    @PUT("defects/{defectId}/status")
    suspend fun changeDefectStatus(
        @Path("defectId") defectId: String, @Body updateStatusModel: UpdateStatusModel
    )

}