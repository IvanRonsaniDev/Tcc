package com.example.tcc.core.extensions

import java.util.Calendar
import java.util.Date

fun Date.resetTime(): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this

    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)

    return calendar.time
}