package dev.ptit.setup.extension

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun Long.longToFormattedDate(format: String = "EE dd/MM · HH:mm"): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this

    val dateFormat = SimpleDateFormat(format, Locale.getDefault())
    return dateFormat.format(calendar.time)
}