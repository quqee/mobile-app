package com.suslanium.hackathon.core.di

import com.suslanium.hackathon.core.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private fun provideLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

private fun provideOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor
): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(20L, TimeUnit.SECONDS)
        .writeTimeout(20L, TimeUnit.SECONDS)
        .readTimeout(20L, TimeUnit.SECONDS)
        .build()


private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    baseUrl: String
): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

fun provideCoreDataModule(): Module = module {

    single { provideLoggingInterceptor() }

    single { provideOkHttpClient(get()) }

    single { provideRetrofit(get(), BASE_URL) }

}