package com.suslanium.hackathon.profile.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.suslanium.hackathon.R
import com.suslanium.hackathon.core.ui.theme.BlueGray
import com.suslanium.hackathon.core.ui.theme.Primary
import com.suslanium.hackathon.core.ui.theme.S14_W400
import com.suslanium.hackathon.core.ui.theme.S15_W600
import com.suslanium.hackathon.core.ui.theme.S20_W700
import com.suslanium.hackathon.createdefect.presentation.ui.screen.components.ErrorContent
import com.suslanium.hackathon.createdefect.presentation.ui.screen.components.LoadingContent
import com.suslanium.hackathon.profile.presentation.state.ProfileUiState
import com.suslanium.hackathon.profile.presentation.viewmodel.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    onNavigateToAuth: () -> Unit,
    viewModel: ProfileViewModel = koinViewModel()
) {
    val uiState by viewModel.profileUiState.collectAsStateWithLifecycle()

    when (uiState) {
        ProfileUiState.Initial -> LoadingContent()
        ProfileUiState.Loading -> LoadingContent()
        ProfileUiState.Error -> ErrorContent(onRetry = viewModel::onRetry)
        is ProfileUiState.Success -> {
            val profile = (uiState as ProfileUiState.Success).profile

            Column(
                Modifier.fillMaxSize().background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.size(112.dp),
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = profile.fullName, style = S20_W700
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = profile.email, style = S14_W400, color = BlueGray
                )

                Spacer(modifier = Modifier.height(5.dp))
                TextButton(
                    onClick = {
                        viewModel.logoutUser()
                        onNavigateToAuth()
                              }, colors = ButtonDefaults.textButtonColors(
                        contentColor = Primary
                    ), modifier = Modifier.fillMaxWidth(0.5f), shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "Выйти из аккаунта", style = S15_W600
                    )
                }
            }
        }
    }
}