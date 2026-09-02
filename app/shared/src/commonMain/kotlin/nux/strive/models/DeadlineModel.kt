package nux.strive.models

import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

data class DeadlineModel(
    val taskName: String,
    val dueDate: LocalDate
) {
    fun getDeadlineStatus(): DeadlineStatus {
        val currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

        val daysUntilDue = currentDate.daysUntil(dueDate)

        return when {
            daysUntilDue < 0 -> DeadlineStatus.OVERDUE
            daysUntilDue == 0 -> DeadlineStatus.DUE
            daysUntilDue in 1..5 -> DeadlineStatus.SOON
            else -> DeadlineStatus.NOT_DUE
        }

    }
}
