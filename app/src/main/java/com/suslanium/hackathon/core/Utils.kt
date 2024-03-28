package com.suslanium.hackathon.core

import java.time.LocalDateTime

fun String.localDateTimeToDate(): String {
    val localDateTime = LocalDateTime.parse(this)
    val date = localDateTime.toLocalDate()
    return date.toString()
}