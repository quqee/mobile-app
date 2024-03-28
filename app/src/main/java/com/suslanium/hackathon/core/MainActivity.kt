package com.suslanium.hackathon.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.suslanium.hackathon.core.ui.theme.HackathonTheme
import android.app.Activity
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.suslanium.hackathon.core.navigation.RootNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val activity = LocalContext.current as? Activity

            RootNavigation(
                navController = navController,
                onCloseApp = {
                    activity?.finish()
                }
            )
        }
    }
}