package com.suslanium.hackathon.onboarding.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import com.suslanium.hackathon.R
import com.suslanium.hackathon.core.ui.common.PrimaryButton
import com.suslanium.hackathon.core.ui.theme.DarkBlue
import com.suslanium.hackathon.core.ui.theme.PaddingLarge
import com.suslanium.hackathon.core.ui.theme.PaddingMedium
import com.suslanium.hackathon.core.ui.theme.PaddingXLarge
import com.suslanium.hackathon.core.ui.theme.PaddingXXLarge
import com.suslanium.hackathon.core.ui.theme.S15_W400
import com.suslanium.hackathon.core.ui.theme.S20_W700

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    onNavigateToAuth: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = PaddingMedium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.7f))

        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.logo),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(PaddingXLarge))

        Text(
            text = stringResource(id = R.string.appName),
            style = S20_W700,
            color = DarkBlue,
        )

        Spacer(modifier = Modifier.height(PaddingLarge))

        Text(
            text = stringResource(id = R.string.startDescription),
            style = S15_W400,
            color = DarkBlue,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            modifier = Modifier.padding(bottom = PaddingXXLarge),
            text = stringResource(id = R.string.logIn),
            onClick = onNavigateToAuth
        )
    }
}