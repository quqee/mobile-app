package com.suslanium.hackathon.core

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun String.localDateTimeToDate(): String {
    val localDateTime = LocalDateTime.parse(this.dropLast(1))
    val date = localDateTime.toLocalDate()
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    return formatter.format(date)
}