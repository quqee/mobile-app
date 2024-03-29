package com.suslanium.hackathon.createdefect.di

import android.content.Context
import com.suslanium.hackathon.core.di.Labels
import com.suslanium.hackathon.createdefect.data.api.CreateDefectApiService
import com.suslanium.hackathon.createdefect.data.repository.CreateDefectRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

private fun provideCreateDefectApiService(retrofit: Retrofit) =
    retrofit.create(CreateDefectApiService::class.java)

private fun provideCreateDefectRepository(
    createDefectApiService: CreateDefectApiService, context: Context
) = CreateDefectRepository(createDefectApiService, context)

fun provideCreateDefectDataModule(): Module = module {

    single { provideCreateDefectApiService(get(named(Labels.TOKEN_RETROFIT_LABEL))) }

    single { provideCreateDefectRepository(get(), androidContext().applicationContext) }

}