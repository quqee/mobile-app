package com.suslanium.hackathon.createdefect.di

import com.suslanium.hackathon.createdefect.presentation.CreateDefectViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun provideCreateDefectPresentationModule() = module {
    viewModel {
        CreateDefectViewModel()
    }
}