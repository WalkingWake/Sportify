package dev.ptit.setup.utils

import dev.ptit.setup.extension.formattedDateToLong
import dev.ptit.setup.extension.longToFormattedDate

object Utils {
    fun checkSameDay(date1: String, date2: String): Boolean {
        return date1.formattedDateToLong()
            .longToFormattedDate("dd/MM/yyyy") == date2.formattedDateToLong()
            .longToFormattedDate("dd/MM/yyyy")
    }
}