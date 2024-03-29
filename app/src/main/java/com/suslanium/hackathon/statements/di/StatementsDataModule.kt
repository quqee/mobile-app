package com.suslanium.hackathon.statements.di

import com.suslanium.hackathon.core.di.Labels.TOKEN_RETROFIT_LABEL
import com.suslanium.hackathon.statements.data.api.StatementApiService
import com.suslanium.hackathon.statements.data.repository.StatementRepository
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

private fun provideStatementApiService(retrofit: Retrofit) =
    retrofit.create(StatementApiService::class.java)

private fun provideStatementRepository(statementApiService: StatementApiService): StatementRepository =
    StatementRepository(statementApiService)

fun provideStatementsDataModule(): Module = module {

    single { provideStatementApiService(get(named(TOKEN_RETROFIT_LABEL))) }

    single { provideStatementRepository(get()) }

}