package com.suslanium.hackathon.defect.di

import com.suslanium.hackathon.defect.data.repository.DefectRepository
import org.koin.dsl.module

fun provideDefectDataModule() = module {
    single {
        DefectRepository()
    }
}