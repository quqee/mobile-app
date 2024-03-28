package com.suslanium.hackathon.auth.di

import com.suslanium.hackathon.auth.presentation.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun provideAuthPresentationModule(): Module = module {

    viewModel { AuthViewModel(get()) }

}