package com.suslanium.hackathon.assignment.di

import com.suslanium.hackathon.assignment.presentation.AssignmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun assignmentProvidePresentationModule(): Module = module {

    viewModel { AssignmentViewModel() }

}