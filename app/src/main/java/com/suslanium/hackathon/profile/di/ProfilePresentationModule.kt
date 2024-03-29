package com.suslanium.hackathon.profile.di

import com.suslanium.hackathon.profile.presentation.viewmodel.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun provideProfilePresentationModule(): Module = module {

    viewModel { ProfileViewModel(get(), get()) }

}