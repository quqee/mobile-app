package com.suslanium.hackathon.profile.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ProfileScreenViewModel : ViewModel() {
    val name = mutableStateOf("Иванов Иван Иванович")
    val company = mutableStateOf("ГК «\u200EНавигатор»")
    val companyRole = mutableStateOf("Исполнитель")

    fun logoutUser() {
        // logout user
    }
}