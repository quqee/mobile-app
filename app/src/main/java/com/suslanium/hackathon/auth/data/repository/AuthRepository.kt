package com.suslanium.hackathon.auth.data.repository

import com.suslanium.hackathon.core.data.api.AuthApiService
import com.suslanium.hackathon.auth.data.model.LoginRequest
import com.suslanium.hackathon.core.Constants.ERROR_STRING
import com.suslanium.hackathon.core.data.datasource.TokenDataSource
import com.suslanium.hackathon.core.data.model.RefreshRequest
import com.suslanium.hackathon.core.data.model.TokenType

class AuthRepository(
    private val authApiService: AuthApiService,
    private val tokenDataSource: TokenDataSource
) {

    suspend fun login(loginRequest: LoginRequest) {
        val tokenResponse = authApiService.login(loginRequest)
        tokenDataSource.saveToken(tokenResponse.accessToken, TokenType.ACCESS)
        tokenDataSource.saveToken(tokenResponse.refreshToken, TokenType.REFRESH)
    }

    suspend fun refresh() {
        val refreshToken = tokenDataSource.fetchToken(TokenType.REFRESH)
        val tokenResponse = authApiService.refresh(
            RefreshRequest(refreshToken ?: ERROR_STRING)
        )
        tokenDataSource.saveToken(tokenResponse.accessToken, TokenType.ACCESS)
        tokenDataSource.saveToken(tokenResponse.refreshToken, TokenType.REFRESH)
    }

    suspend fun logout() {
        val refreshToken = tokenDataSource.fetchToken(TokenType.REFRESH)
        authApiService.logout(RefreshRequest(refreshToken ?: ERROR_STRING))
        tokenDataSource.removeTokens()
    }

    fun hasToken(): Boolean {
        return tokenDataSource.fetchToken(TokenType.ACCESS) != null
    }
}