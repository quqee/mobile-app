package com.suslanium.hackathon.core

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.suslanium.hackathon.core.navigation.RootNavigation
import com.yandex.mapkit.MapKitFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window = this.window
        window.statusBarColor = Color.White.toArgb()
        window.navigationBarColor = Color.White.toArgb()
        MapKitFactory.initialize(this)
        setContent {
            val view = LocalView.current
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
            val navController = rememberNavController()
            val activity = LocalContext.current as? Activity

            RootNavigation(navController = navController, onCloseApp = {
                activity?.finish()
            })
        }
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
    }

    override fun onStop() {
        super.onStop()
        MapKitFactory.getInstance().onStop()
    }
}