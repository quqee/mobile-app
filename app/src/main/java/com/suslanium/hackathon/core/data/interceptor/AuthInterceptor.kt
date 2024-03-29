package com.suslanium.hackathon.core.data.interceptor

import com.suslanium.hackathon.core.Constants.AUTHORIZATION_HEADER
import com.suslanium.hackathon.core.data.datasource.TokenDataSource
import com.suslanium.hackathon.core.data.model.TokenType
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val tokenDataSource: TokenDataSource
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()

        if (request.header(AUTHORIZATION_HEADER) == null) {
            tokenDataSource.fetchToken(TokenType.ACCESS)?.let {
                builder.addHeader(
                    name = AUTHORIZATION_HEADER,
                    value = "Bearer $it"
                )
            }
        }

        return chain.proceed(builder.build())
    }

}