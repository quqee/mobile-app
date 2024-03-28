package com.suslanium.hackathon.core.navigation
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomNavigationWrapper(
    rootNavController: NavHostController,
    onCloseApp: () -> Unit,
) {
    val bottomNavController = rememberNavController()

    BackHandler {
        if (bottomNavController.previousBackStackEntry == null) {
            onCloseApp()
        } else {
            bottomNavController.popBackStack()
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                buttons = BottomNavItems.items,
                navController = bottomNavController,
                onItemClick = {
                    bottomNavController.navigate(it.route)
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            BottomNavigation(
                bottomNavController = bottomNavController,
                rootNavController = rootNavController
            )
        }
    }
}