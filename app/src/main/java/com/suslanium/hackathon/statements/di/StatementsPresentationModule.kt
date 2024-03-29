package com.suslanium.hackathon.statements.di

import com.suslanium.hackathon.statements.presentation.viewmodel.CreateStatementViewModel
import com.suslanium.hackathon.statements.presentation.viewmodel.StatementViewModel
import com.suslanium.hackathon.statements.presentation.viewmodel.StatementsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun provideStatementsPresentationModule(): Module = module {

    viewModel { StatementsViewModel(get(), get()) }

    viewModel { parameters ->
        StatementViewModel(
            statementId = parameters.get(),
            get(), get()
        )
    }

    viewModel { CreateStatementViewModel(get(), get()) }

}