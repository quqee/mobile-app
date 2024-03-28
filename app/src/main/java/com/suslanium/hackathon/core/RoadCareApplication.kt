package com.suslanium.hackathon.core

import android.app.Application
import com.suslanium.hackathon.BuildConfig
import com.yandex.mapkit.MapKitFactory
import com.suslanium.hackathon.auth.di.provideAuthDataModule
import com.suslanium.hackathon.auth.di.provideAuthPresentationModule
import com.suslanium.hackathon.core.di.provideCoreDataModule
import com.suslanium.hackathon.di.providePresentationModule
import com.suslanium.hackathon.profile.di.profileProvidePresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RoadCareApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey(BuildConfig.MAPKIT_API_KEY)
        startKoin {
            androidContext(this@RoadCareApplication)
            modules(
                providePresentationModule(),
              
                provideCoreDataModule(),

                provideAuthDataModule(),
                provideAuthPresentationModule(),
              
                profileProvidePresentationModule()
            )
        }
    }

}