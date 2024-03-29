package com.suslanium.hackathon.splash.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suslanium.hackathon.auth.data.repository.AuthRepository
import com.suslanium.hackathon.splash.event.SplashEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SplashViewModel(authRepository: AuthRepository) : ViewModel() {

    private val splashEventChannel = Channel<SplashEvent>()
    val splashEventFlow = splashEventChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            if (authRepository.hasToken()) {
                splashEventChannel.send(SplashEvent.UserAuthenticated)
            } else {
                splashEventChannel.send(SplashEvent.AuthenticationRequired)
            }
        }
    }

}