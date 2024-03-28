package com.suslanium.hackathon.createdefect.di

import com.suslanium.hackathon.createdefect.data.repository.CreateDefectRepository
import org.koin.core.module.Module
import org.koin.dsl.module

fun provideCreateDefectDataModule(): Module = module {

    single { CreateDefectRepository() }

}