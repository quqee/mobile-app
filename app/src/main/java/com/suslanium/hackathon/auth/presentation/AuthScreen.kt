package com.suslanium.hackathon.auth.presentation

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.suslanium.hackathon.R
import com.suslanium.hackathon.core.ui.common.AppOutlinedTextField
import com.suslanium.hackathon.core.ui.common.LoadingPrimaryButton
import com.suslanium.hackathon.core.ui.common.PrimaryButton
import com.suslanium.hackathon.core.ui.common.RoadCareTopBar
import com.suslanium.hackathon.core.ui.theme.Error
import com.suslanium.hackathon.core.ui.theme.LightGray
import com.suslanium.hackathon.core.ui.theme.PaddingMedium
import com.suslanium.hackathon.core.ui.theme.PaddingRegular
import com.suslanium.hackathon.core.ui.theme.S12_W400
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthScreen(
    onNavigateBack: () -> Unit,
    onNavigateToStatements: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = koinViewModel(),
) {
    val focusManager = LocalFocusManager.current
    val uiState by viewModel.authUiState.collectAsStateWithLifecycle()
    val authState by viewModel.authState.collectAsStateWithLifecycle()

    if (uiState is AuthUiState.Success) {
        LaunchedEffect(Unit) {
            onNavigateToStatements()
        }
    }

    Scaffold(
        topBar = {
            RoadCareTopBar(
                text = stringResource(id = R.string.logInAuth),
                onBackButtonClick = onNavigateBack
            )
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = PaddingMedium)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            focusManager.clearFocus()
                        }
                    )
                }
                .verticalScroll(rememberScrollState())
        ) {
            AppOutlinedTextField(
                value = authState.email,
                onValueChange = viewModel::onEmailChange,
                label = stringResource(id = R.string.email)
            )

            Spacer(modifier = Modifier.height(PaddingMedium))

            AppOutlinedTextField(
                value = authState.password,
                onValueChange = viewModel::onPasswordChange,
                label = stringResource(id = R.string.password),
                trailingIcon = {
                    IconButton(
                        onClick = viewModel::onPasswordVisibilityChange
                    ) {
                        val icon = if (authState.isPasswordHidden) {
                            ImageVector.vectorResource(id = R.drawable.ic_visibility_show)
                        } else {
                            ImageVector.vectorResource(id = R.drawable.ic_visibility_hide)
                        }

                        Icon(
                            imageVector = icon,
                            contentDescription = stringResource(id = R.string.changePasswordVisibility),
                            tint = LightGray
                        )
                    }
                },
                visualTransformation = if (authState.isPasswordHidden) PasswordVisualTransformation()
                    else VisualTransformation.None
            )

            if (uiState is AuthUiState.Error) {
                Text(
                    text = stringResource(id = R.string.authError),
                    style = S12_W400,
                    color = Error
                )
            }

            Spacer(modifier = Modifier.height(PaddingRegular))

            if (uiState is AuthUiState.Loading) {
                LoadingPrimaryButton()
            } else {
                PrimaryButton(
                    text = stringResource(id = R.string.logIn),
                    onClick = viewModel::onLogin,
                    enabled = authState.email.isNotBlank() && authState.password.isNotBlank()
                )
            }
        }
    }
}