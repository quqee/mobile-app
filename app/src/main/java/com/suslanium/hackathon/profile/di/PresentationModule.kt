package com.suslanium.hackathon.profile.di

import com.suslanium.hackathon.profile.presentation.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.scope.get
import org.koin.dsl.module

fun profileProvidePresentationModule(): Module = module {

    viewModel { ProfileViewModel() }

}