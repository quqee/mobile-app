package com.suslanium.hackathon.core.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.suslanium.hackathon.auth.presentation.AuthScreen
import com.suslanium.hackathon.createdefect.presentation.ui.screen.CreateDefectScreen
import com.suslanium.hackathon.onboarding.presentation.OnboardingScreen

object RoadCareDestinations {
    // Root
    const val ONBOARDING = "onboarding"
    const val AUTH = "auth"
    const val CREATE_DEFECT = "create_defect"

    // Bottom navigation
    const val STATEMENTS = "statements"
    const val MAP = "map"
    const val PROFILE = "profile"
}

@Composable
fun RootNavigation(
    navController: NavHostController,
    onCloseApp: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = RoadCareDestinations.ONBOARDING
    ) {
        composable(RoadCareDestinations.ONBOARDING) {
            OnboardingScreen(
                onNavigateToAuth = {
                    navController.navigate(RoadCareDestinations.AUTH)
                }
            )
        }
        composable(RoadCareDestinations.AUTH) {
            AuthScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToStatements = {
                    navController.navigate(RoadCareDestinations.STATEMENTS)
                }
            )
        }
        composable(RoadCareDestinations.STATEMENTS) {
            // Navigate to this route to enter bottom navigation container
            BottomNavigationWrapper(
                rootNavController = navController,
                onCloseApp = onCloseApp
            )
        }
        composable(RoadCareDestinations.CREATE_DEFECT) {
            CreateDefectScreen {
                navController.popBackStack()
            }
        }
    }
}

@Composable
fun PlaceholderScreen(
    text: String,
    onButtonClick: () -> Unit,
    buttonText: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = text
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onButtonClick
            ) {
                Text(
                    text = buttonText
                )
            }
        }
    }
}