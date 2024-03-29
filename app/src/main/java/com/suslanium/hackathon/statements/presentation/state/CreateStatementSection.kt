package com.suslanium.hackathon.statements.presentation.state

sealed interface CreateStatementSection {

    data object CreationForm : CreateStatementSection

    data object RoadTypes : CreateStatementSection

    data object SurfaceTypes : CreateStatementSection

}