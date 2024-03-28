package com.suslanium.hackathon.core

import android.app.Application
import com.suslanium.hackathon.BuildConfig
import com.yandex.mapkit.MapKitFactory

class RoadCareApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey(BuildConfig.MAPKIT_API_KEY)
    }

}