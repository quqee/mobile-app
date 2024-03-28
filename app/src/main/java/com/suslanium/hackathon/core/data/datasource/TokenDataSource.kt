package com.suslanium.hackathon.core.data.datasource

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.suslanium.hackathon.core.Constants.TOKEN_PREFERENCES_KEY
import com.suslanium.hackathon.core.Constants.USER_ACCESS_TOKEN
import com.suslanium.hackathon.core.Constants.USER_REFRESH_TOKEN
import com.suslanium.hackathon.core.data.model.TokenType

class TokenDataSource(context: Context) {

    private val masterKeyAlias = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val sharedPreferences = EncryptedSharedPreferences.create(
        context,
        TOKEN_PREFERENCES_KEY,
        masterKeyAlias,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun fetchToken(tokenType: TokenType): String? {
        return when (tokenType) {
            TokenType.ACCESS -> sharedPreferences.getString(USER_ACCESS_TOKEN, null)
            TokenType.REFRESH -> sharedPreferences.getString(USER_REFRESH_TOKEN, null)
        }
    }

    fun saveToken(token: String, tokenType: TokenType) {
        when (tokenType) {
            TokenType.ACCESS -> {
                sharedPreferences.edit()
                    .putString(USER_ACCESS_TOKEN, token)
                    .apply()
            }
            TokenType.REFRESH -> {
                sharedPreferences.edit()
                    .putString(USER_REFRESH_TOKEN, token)
                    .apply()
            }
        }
    }

    fun removeTokens() {
        sharedPreferences.edit()
            .remove(USER_ACCESS_TOKEN)
            .remove(USER_REFRESH_TOKEN)
            .apply()
    }

}