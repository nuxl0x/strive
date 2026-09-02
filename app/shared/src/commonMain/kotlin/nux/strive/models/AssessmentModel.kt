package nux.strive.models

import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

data class AssessmentModel(
    val name: String,
    val dueDate: LocalDate
) {
    fun getDeadlineStatus(): Status {
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
