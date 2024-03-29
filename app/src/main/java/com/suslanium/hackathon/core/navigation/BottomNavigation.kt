package com.suslanium.hackathon.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.suslanium.hackathon.profile.presentation.screen.ProfileScreen
import com.suslanium.hackathon.statements.presentation.screen.StatementsScreen

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
            StatementsScreen(
                onNavigateToStatement = {
                    rootNavController.navigate("${RoadCareDestinations.STATEMENT}/$it")
                },
                onNavigateToCreateStatement = {
                    rootNavController.navigate(RoadCareDestinations.CREATE_STATEMENT)
                }
            )
        }
        composable(RoadCareDestinations.MAP) {
            PlaceholderScreen(
                text = "map",
                onButtonClick = {},
                buttonText = "placeholder"
            )
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