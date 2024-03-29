package com.suslanium.hackathon.defect.data.model

import com.suslanium.hackathon.core.ui.common.Status

enum class DefectStatus(val statusAlternative: Status) {
    IN_PROCESS(Status.IN_PROGRESS),
    COMPLETED(Status.DONE),
    REJECTED(Status.CANCELLED)
}