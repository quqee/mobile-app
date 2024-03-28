package com.suslanium.hackathon.core.di

import com.suslanium.hackathon.core.Constants.BASE_URL
import com.suslanium.hackathon.core.data.datasource.TokenDataSource
import com.suslanium.hackathon.core.data.interceptor.AuthInterceptor
import com.suslanium.hackathon.core.di.Labels.REGULAR_HTTP_CLIENT_LABEL
import com.suslanium.hackathon.core.di.Labels.REGULAR_RETROFIT_LABEL
import com.suslanium.hackathon.core.di.Labels.TOKEN_HTTP_CLIENT_LABEL
import com.suslanium.hackathon.core.di.Labels.TOKEN_RETROFIT_LABEL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private fun provideLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

private fun provideAuthInterceptor(tokenDataSource: TokenDataSource): AuthInterceptor =
    AuthInterceptor(tokenDataSource)

private fun provideOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor
): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(20L, TimeUnit.SECONDS)
        .writeTimeout(20L, TimeUnit.SECONDS)
        .readTimeout(20L, TimeUnit.SECONDS)
        .build()

private fun provideTokenOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor,
    authInterceptor: AuthInterceptor
): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(authInterceptor)
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

    single { provideAuthInterceptor(get()) }

    single(named(REGULAR_HTTP_CLIENT_LABEL)) { provideOkHttpClient(get()) }

    single(named(TOKEN_HTTP_CLIENT_LABEL)) { provideTokenOkHttpClient(get(), get()) }

    single(named(REGULAR_RETROFIT_LABEL)) { provideRetrofit(get(named(REGULAR_HTTP_CLIENT_LABEL)), BASE_URL) }

    single(named(TOKEN_RETROFIT_LABEL)) { provideRetrofit(get(named(TOKEN_HTTP_CLIENT_LABEL)), BASE_URL) }

}