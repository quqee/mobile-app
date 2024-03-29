package com.suslanium.hackathon.assignment.di

import com.suslanium.hackathon.profile.presentation.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun assignmentProvidePresentationModule(): Module = module {

    viewModel { ProfileViewModel() }

}