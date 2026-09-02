package nux.strive.util

import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

fun LocalDate.format(shouldComparativeFormat: Boolean = false): String {
    val currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

    val day = if (currentDate == this && shouldComparativeFormat) {
        "Today"
    } else {
        this.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }
    }
    val month = this.month.name.lowercase().replaceFirstChar { it.uppercase() }
    val dayOfMonth = this.day

    val lastNumber = dayOfMonth.toString().last().toString()
    val suffix = when (lastNumber) {
        "1" -> "st"
        "2" -> "nd"
        "3" -> "rd"
        else -> "th"
    }

    return "$day, $month $dayOfMonth$suffix"

}