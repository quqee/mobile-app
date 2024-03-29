package com.suslanium.hackathon.core

import android.app.Application
import com.suslanium.hackathon.BuildConfig
import com.suslanium.hackathon.auth.di.provideAuthDataModule
import com.suslanium.hackathon.auth.di.provideAuthPresentationModule
import com.suslanium.hackathon.core.di.provideCoreDataModule
import com.suslanium.hackathon.createdefect.di.provideCreateDefectDataModule
import com.suslanium.hackathon.createdefect.di.provideCreateDefectPresentationModule
import com.suslanium.hackathon.defect.di.defectProvidePresentationModule
import com.suslanium.hackathon.defect.di.provideDefectDataModule
import com.suslanium.hackathon.di.providePresentationModule
import com.suslanium.hackathon.profile.di.profileProvidePresentationModule
import com.suslanium.hackathon.splash.di.provideSplashPresentationModule
import com.suslanium.hackathon.statements.di.provideStatementsDataModule
import com.suslanium.hackathon.statements.di.provideStatementsPresentationModule
import com.yandex.mapkit.MapKitFactory
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

                provideStatementsDataModule(),
                provideStatementsPresentationModule(),

                profileProvidePresentationModule(),

                provideCreateDefectPresentationModule(),

                provideDefectDataModule(),
                defectProvidePresentationModule(),
                provideCreateDefectDataModule(),
                provideCreateDefectPresentationModule(),

                provideSplashPresentationModule()
            )
        }
    }

}