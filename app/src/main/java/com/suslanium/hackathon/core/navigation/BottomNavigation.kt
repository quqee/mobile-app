package com.suslanium.hackathon.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.suslanium.hackathon.statements.presentation.StatementsScreen

@Composable
fun BottomNavigation(
    bottomNavController: NavHostController,
    rootNavController: NavHostController,
) {
    // Use bottomNavController to navigate between inner BottomNavigation routes
    // Use rootNavController to navigate to routes from RootNavigation

    NavHost(
        navController = bottomNavController,
        startDestination = RoadCareDestinations.STATEMENTS
    ) {
        composable(RoadCareDestinations.STATEMENTS) {
            StatementsScreen()
        }
        composable(RoadCareDestinations.MAP) {
            PlaceholderScreen(
                text = "map",
                onButtonClick = {},
                buttonText = "placeholder"
            )
        }
        composable(RoadCareDestinations.PROFILE) {
            PlaceholderScreen(
                text = "profile",
                onButtonClick = {},
                buttonText = "placeholder"
            )
        }
    }
}