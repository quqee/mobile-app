package com.suslanium.hackathon.defect.di

import com.suslanium.hackathon.defect.presentation.viewmodel.DefectViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun defectProvidePresentationModule(): Module = module {
    viewModel {
        DefectViewModel(it.get(), get())
    }
}