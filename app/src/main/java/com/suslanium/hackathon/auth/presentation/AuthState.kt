package com.suslanium.hackathon.auth.presentation

data class AuthState(
    val email: String = "",
    val password: String = "",
    val isPasswordHidden: Boolean = true
)
