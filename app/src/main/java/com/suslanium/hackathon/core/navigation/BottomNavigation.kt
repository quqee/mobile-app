package com.suslanium.hackathon.core.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.suslanium.hackathon.defect.presentation.ui.components.MapView
import com.suslanium.hackathon.profile.presentation.screen.ProfileScreen
import com.suslanium.hackathon.statements.presentation.screen.StatementsScreen
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition

@Composable
fun BottomNavigation(
    bottomNavController: NavHostController,
    rootNavController: NavHostController,
) {
    // Use bottomNavController to navigate between inner BottomNavigation routes
    // Use rootNavController to navigate to routes from RootNavigation

    NavHost(
        modifier = Modifier.background(Color.White),
        navController = bottomNavController,
        startDestination = RoadCareDestinations.STATEMENTS
    ) {
        composable(RoadCareDestinations.STATEMENTS) {
            StatementsScreen(
                modifier = Modifier.background(Color.White),
                onNavigateToStatement = {
                    rootNavController.navigate("${RoadCareDestinations.STATEMENT}/$it")
                },
                onNavigateToCreateStatement = {
                    rootNavController.navigate(RoadCareDestinations.CREATE_STATEMENT)
                }
            )
        }
        composable(RoadCareDestinations.MAP) {
            MapView(startCameraPosition = CameraPosition(
                Point(57.993764, 56.216534),
                17.0f, 150.0f, 30.0f
            ), modifier = Modifier.fillMaxSize(), blockInput = false)
        }
        composable(RoadCareDestinations.PROFILE) {
            ProfileScreen(
                onNavigateToAuth = {
                    rootNavController.navigate(RoadCareDestinations.AUTH) {
                        popUpTo(RoadCareDestinations.AUTH) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}