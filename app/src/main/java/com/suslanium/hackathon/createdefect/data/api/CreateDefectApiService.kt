package com.suslanium.hackathon.createdefect.data.api

import com.suslanium.hackathon.createdefect.data.model.DefectType
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CreateDefectApiService {

    @POST("defects")
    suspend fun createDefect(@Body body: RequestBody)

    @GET("defects/types")
    suspend fun getDefectTypes(): List<DefectType>

}