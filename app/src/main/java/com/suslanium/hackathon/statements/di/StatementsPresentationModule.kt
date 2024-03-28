package com.suslanium.hackathon.statements.di

import com.suslanium.hackathon.statements.presentation.viewmodel.StatementsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun provideStatementsPresentationModule(): Module = module {

    viewModel { StatementsViewModel(get(), get()) }

}