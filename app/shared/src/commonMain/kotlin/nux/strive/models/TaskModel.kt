package nux.strive.models

import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.Duration

data class TaskModel(
    val name: String,
    val duration: Duration,
    val subject: String,
    val dueDate: LocalDate? = null,
) {
    fun getDeadlineStatus(): Status {
        if (dueDate == null) return Status.NOT_DUE

        val currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

        val daysUntilDue = currentDate.daysUntil(dueDate)

        return when {
            daysUntilDue < 0 -> Status.OVERDUE
            daysUntilDue == 0 -> Status.DUE
            daysUntilDue in 1..5 -> Status.SOON
            else -> Status.NOT_DUE
        }

    }
}