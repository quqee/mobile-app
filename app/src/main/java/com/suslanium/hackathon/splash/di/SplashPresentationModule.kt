package com.suslanium.hackathon.splash.di

import com.suslanium.hackathon.splash.presentation.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun provideSplashPresentationModule(): Module = module {

    viewModel { SplashViewModel(get()) }

}