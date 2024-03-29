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
import com.suslanium.hackathon.assignment.presentation.Assignment
import com.suslanium.hackathon.auth.presentation.AuthScreen
import com.suslanium.hackathon.createdefect.presentation.ui.screen.CreateDefectScreen
import com.suslanium.hackathon.defect.presentation.ui.AddReviewScreen
import com.suslanium.hackathon.defect.presentation.ui.DefectScreen
import com.suslanium.hackathon.intro.presentation.IntroScreen
import com.suslanium.hackathon.onboarding.presentation.OnboardingScreen
import com.suslanium.hackathon.splash.presentation.SplashScreen
import com.suslanium.hackathon.statements.presentation.screen.CreateStatementScreen
import com.suslanium.hackathon.statements.presentation.screen.StatementScreen
import java.util.UUID

object RoadCareDestinations {
    // Root
    const val SPLASH = "splash"
    const val ONBOARDING = "onboarding"
    const val AUTH = "auth"
    const val CREATE_DEFECT = "create_defect"
    const val DEFECT = "defect"
    const val ADD_REVIEW = "add_review"
    const val STATEMENT = "statement"
    const val CREATE_STATEMENT = "create_statement"
    const val INTRO = "intro"
    const val ASSIGNMENT = "assignment"

    // Bottom navigation
    const val STATEMENTS = "statements"
    const val MAP = "map"
    const val PROFILE = "profile"
}

@Composable
fun RootNavigation(
    navController: NavHostController, onCloseApp: () -> Unit
) {
    NavHost(
        navController = navController, startDestination = RoadCareDestinations.SPLASH
    ) {
        composable(RoadCareDestinations.ONBOARDING) {
            OnboardingScreen(onNavigateToAuth = {
                navController.navigate(RoadCareDestinations.AUTH)
            })
        }
        composable(RoadCareDestinations.AUTH) {
            AuthScreen(onNavigateBack = {
                navController.popBackStack()
            }, onNavigateToStatements = {
                navController.navigate(RoadCareDestinations.STATEMENTS)
            })
        }
        composable(RoadCareDestinations.STATEMENTS) {
            // Navigate to this route to enter bottom navigation container
            BottomNavigationWrapper(
                rootNavController = navController, onCloseApp = onCloseApp
            )
        }
        composable(
            route = "${RoadCareDestinations.CREATE_DEFECT}/{statementId}",
            arguments = listOf(navArgument("statementId") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val statementId = backStackEntry.arguments?.getString("statementId") ?: ""

            CreateDefectScreen(statementId = statementId, onNavigateAfterSuccess = {
                navController.popBackStack()
                navController.popBackStack()
                navController.navigate("${RoadCareDestinations.STATEMENT}/$statementId")
            }, onNavigateBack = {
                navController.popBackStack()
            })
        }
        composable(
            route = "${RoadCareDestinations.STATEMENT}/{statementId}",
            arguments = listOf(navArgument("statementId") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val statementId = backStackEntry.arguments?.getString("statementId") ?: ""

            StatementScreen(modifier = Modifier.background(Color.White),
                statementId = statementId,
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToCreateDefect = {
                    navController.navigate("${RoadCareDestinations.CREATE_DEFECT}/$it")
                },
                onNavigateToDefect = {
                    navController.navigate("${RoadCareDestinations.DEFECT}/$it")
                },
                onNavigateToAssignment = {
                    navController.navigate("${RoadCareDestinations.ASSIGNMENT}/$statementId")
                })
        }
        composable(
            route = "${RoadCareDestinations.DEFECT}/{defectId}",
            arguments = listOf(navArgument("defectId") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val defectId = backStackEntry.arguments?.getString("defectId") ?: ""

            DefectScreen(defectId = defectId, onNavigateToAddReview = {
                navController.navigate("${RoadCareDestinations.ADD_REVIEW}/$defectId")
            })
        }
        composable(
            route = "${RoadCareDestinations.ADD_REVIEW}/{defectId}",
            arguments = listOf(navArgument("defectId") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val defectId = backStackEntry.arguments?.getString("defectId") ?: ""

            AddReviewScreen(defectId = defectId,
                onBack = navController::popBackStack,
                onNavigateAfterSuccess = {
                    navController.popBackStack()
                    navController.popBackStack()
                    navController.navigate("${RoadCareDestinations.DEFECT}/$defectId")
                })
        }
        composable(RoadCareDestinations.CREATE_STATEMENT) {
            CreateStatementScreen(onNavigateBack = {
                navController.popBackStack()
                navController.popBackStack()
                navController.navigate(RoadCareDestinations.STATEMENTS)
            })
        }
        composable(RoadCareDestinations.SPLASH) {
            SplashScreen(onNavigateToIntro = {
                navController.navigate(RoadCareDestinations.INTRO)
            }, onNavigateToStatements = {
                navController.navigate(RoadCareDestinations.STATEMENTS)
            })
        }
        composable(RoadCareDestinations.INTRO) {
            IntroScreen {
                navController.navigate(RoadCareDestinations.ONBOARDING)
            }
        }
        composable(
            "${RoadCareDestinations.ASSIGNMENT}/{statementId}",
            arguments = listOf(navArgument("statementId") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val statementId = backStackEntry.arguments?.getString("statementId") ?: ""

            Assignment(statementId = UUID.fromString(statementId), onConfirm = {
                navController.popBackStack()
            })
        }
    }
}

@Composable
fun PlaceholderScreen(
    text: String, onButtonClick: () -> Unit, buttonText: String
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