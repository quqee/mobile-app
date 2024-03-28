package com.suslanium.hackathon.auth.di

import com.suslanium.hackathon.core.data.api.AuthApiService
import com.suslanium.hackathon.auth.data.repository.AuthRepository
import com.suslanium.hackathon.core.data.datasource.TokenDataSource
import com.suslanium.hackathon.core.di.Labels.REGULAR_RETROFIT_LABEL
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit


private fun provideAuthApiService(retrofit: Retrofit): AuthApiService =
    retrofit.create(AuthApiService::class.java)

private fun provideAuthRepository(
    authApiService: AuthApiService,
    tokenDataSource: TokenDataSource
): AuthRepository =
    AuthRepository(authApiService, tokenDataSource)
fun provideAuthDataModule(): Module = module {

    single { TokenDataSource(androidContext().applicationContext) }

    single { provideAuthApiService(get(named(REGULAR_RETROFIT_LABEL))) }

    single { provideAuthRepository(get(), get()) }

}