package com.suslanium.hackathon.statements.data.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.suslanium.hackathon.R
import com.suslanium.hackathon.core.ui.theme.CompletedStatusColor
import com.suslanium.hackathon.core.ui.theme.InProgressStatusColor
import com.suslanium.hackathon.core.ui.theme.LightGray
import com.suslanium.hackathon.core.ui.theme.RejectedStatusColor
import com.suslanium.hackathon.core.ui.theme.WaitingStatusColor

enum class StatementStatus(
    val description: String,
    @DrawableRes val iconId: Int,
    val color: Color
) {
    OPEN("ОТКРЫТА", R.drawable.crop_free, LightGray),
    REJECTED("ОТКЛОНЕНА", R.drawable.ic_reject, RejectedStatusColor),
    IN_PROCESS("В РАБОТЕ", R.drawable.ic_in_progress, InProgressStatusColor),
    WAIT_ACCEPT("ОЖИДАЕТ ПОДТВЕРЖДЕНИЯ", R.drawable.ic_waiting, WaitingStatusColor),
    COMPLETED("ВЫПОЛНЕНА", R.drawable.ic_completed, CompletedStatusColor)
}