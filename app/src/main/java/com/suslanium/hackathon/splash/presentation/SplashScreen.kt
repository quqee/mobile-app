package com.suslanium.hackathon.splash.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.suslanium.hackathon.R
import com.suslanium.hackathon.createdefect.presentation.ui.common.ObserveAsEvents
import com.suslanium.hackathon.splash.event.SplashEvent
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    onNavigateToIntro: () -> Unit,
    onNavigateToStatements: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = koinViewModel()
) {
    ObserveAsEvents(flow = viewModel.splashEventFlow) { event ->
        when (event) {
            SplashEvent.AuthenticationRequired -> onNavigateToIntro()
            SplashEvent.UserAuthenticated -> onNavigateToStatements()
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.logo),
            contentDescription = null
        )
    }
}