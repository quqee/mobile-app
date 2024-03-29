package com.suslanium.hackathon.splash.event

sealed interface SplashEvent {

    data object AuthenticationRequired : SplashEvent

    data object UserAuthenticated : SplashEvent

}