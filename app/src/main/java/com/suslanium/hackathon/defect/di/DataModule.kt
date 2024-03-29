package com.suslanium.hackathon.defect.di

import android.content.Context
import com.suslanium.hackathon.core.di.Labels
import com.suslanium.hackathon.defect.data.api.DefectApiService
import com.suslanium.hackathon.defect.data.repository.DefectRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

private fun provideDefectApiService(retrofit: Retrofit) =
    retrofit.create(DefectApiService::class.java)

private fun provideDefectRepository(
    defectApiService: DefectApiService, context: Context
) = DefectRepository(defectApiService, context)

fun provideDefectDataModule() = module {
    single {
        provideDefectApiService(get(named(Labels.TOKEN_RETROFIT_LABEL)))
    }

    single {
        provideDefectRepository(get(), androidContext().applicationContext)
    }
}