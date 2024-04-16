package dev.ptit.setup.extension

import java.text.SimpleDateFormat
import java.util.Locale

fun String.formattedDateToLong(): Long {
    val format = SimpleDateFormat("dd:MM:yy HH:mm", Locale.getDefault())
    val date = format.parse(this)
    return date?.time ?: -1
}