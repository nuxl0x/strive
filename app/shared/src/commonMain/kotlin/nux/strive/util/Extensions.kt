package nux.strive.util

import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.Duration

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

    return if (dayOfMonth == 11 || dayOfMonth == 12 || dayOfMonth == 13) {
        "$day, $month ${dayOfMonth}th"
    } else {
        "$day, $month $dayOfMonth$suffix"
    }

}

fun Duration.format(): String {
    val (hours, minutes) = toComponents { hours, minutes, _, _ ->
        Pair(hours, minutes)
    }

    val parts = mutableListOf<String>()
    if (hours > 0) parts.add("${hours}h")
    if (minutes > 0) parts.add("${minutes}m")

    return parts.joinToString(" ").ifEmpty { "0m" }
}