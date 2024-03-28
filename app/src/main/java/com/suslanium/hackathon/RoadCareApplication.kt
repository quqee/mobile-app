package com.suslanium.hackathon

import android.app.Application
import com.yandex.mapkit.MapKitFactory

class RoadCareApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey(BuildConfig.MAPKIT_API_KEY)
    }

}