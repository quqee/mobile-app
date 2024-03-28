package com.suslanium.hackathon.statements.data.model

enum class StatementStatus(val description: String) {
    OPEN("Открыта"),
    REJECTED("Отклонена"),
    IN_PROCESS("В работе"),
    WAIT_ACCEPT("Ожидает подтверждения"),
    COMPLETED("Выполнена")
}