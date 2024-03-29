package com.suslanium.hackathon.profile.di

import com.suslanium.hackathon.core.di.Labels.TOKEN_RETROFIT_LABEL
import com.suslanium.hackathon.profile.data.api.ProfileApiService
import com.suslanium.hackathon.profile.data.repository.ProfileRepository
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

private fun provideProfileApiService(retrofit: Retrofit): ProfileApiService =
    retrofit.create(ProfileApiService::class.java)

private fun provideProfileRepository(profileApiService: ProfileApiService): ProfileRepository =
    ProfileRepository(profileApiService)

fun provideProfileDataModule(): Module = module {

    single { provideProfileApiService(get(named(TOKEN_RETROFIT_LABEL))) }

    single { provideProfileRepository(get()) }

}