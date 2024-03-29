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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.suslanium.hackathon.auth.presentation.AuthScreen
import com.suslanium.hackathon.createdefect.presentation.ui.screen.CreateDefectScreen
import com.suslanium.hackathon.defect.presentation.ui.DefectScreen
import com.suslanium.hackathon.onboarding.presentation.OnboardingScreen
import com.suslanium.hackathon.splash.presentation.SplashScreen
import com.suslanium.hackathon.statements.presentation.screen.CreateStatementScreen
import com.suslanium.hackathon.statements.presentation.screen.StatementScreen

object RoadCareDestinations {
    // Root
    const val SPLASH = "splash"
    const val ONBOARDING = "onboarding"
    const val AUTH = "auth"
    const val CREATE_DEFECT = "create_defect"
    const val DEFECT = "defect"
    const val STATEMENT = "statement"
    const val CREATE_STATEMENT = "create_statement"

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
        startDestination = RoadCareDestinations.SPLASH
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
        composable(
            route = "${RoadCareDestinations.CREATE_DEFECT}/{statementId}",
            arguments = listOf(
                navArgument("statementId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val statementId = backStackEntry.arguments?.getString("statementId") ?: ""

            CreateDefectScreen(
                statementId = statementId,
                onNavigateAfterSuccess = {
                    navController.popBackStack()
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = "${RoadCareDestinations.STATEMENT}/{statementId}",
            arguments = listOf(
                navArgument("statementId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val statementId = backStackEntry.arguments?.getString("statementId") ?: ""

            StatementScreen(
                statementId = statementId,
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToCreateDefect = {
                    navController.navigate("${RoadCareDestinations.CREATE_DEFECT}/$it")
                },
                onNavigateToDefect = {
                    navController.navigate("${RoadCareDestinations.DEFECT}/$it")
                }
            )
        }
        composable(
            route = "${RoadCareDestinations.DEFECT}/{defectId}",
            arguments = listOf(
                navArgument("defectId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val defectId = backStackEntry.arguments?.getString("defectId") ?: ""

            DefectScreen(defectId = defectId, onCloseScreen = navController::popBackStack)
        }
        composable(RoadCareDestinations.CREATE_STATEMENT) {
            CreateStatementScreen(
                onNavigateBack = {
                    navController.popBackStack()
                    navController.popBackStack()
                    navController.navigate(RoadCareDestinations.STATEMENTS)
                }
            )
        }
        composable(RoadCareDestinations.SPLASH) {
            SplashScreen(
                onNavigateToOnboarding = {
                    navController.navigate(RoadCareDestinations.ONBOARDING)
                },
                onNavigateToStatements = {
                    navController.navigate(RoadCareDestinations.STATEMENTS)
                }
            )
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