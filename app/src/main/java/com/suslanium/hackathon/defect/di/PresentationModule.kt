package com.suslanium.hackathon.defect.di

import com.suslanium.hackathon.profile.presentation.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.scope.get
import org.koin.dsl.module

fun defectProvidePresentationModule(): Module = module {

    viewModel { ProfileViewModel() }

}