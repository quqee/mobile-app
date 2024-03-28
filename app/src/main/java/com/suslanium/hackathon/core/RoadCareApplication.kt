package com.suslanium.hackathon.core

import android.app.Application
import com.suslanium.hackathon.di.providePresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RoadCareApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RoadCareApplication)
            modules(
                providePresentationModule()
            )
        }
    }

}