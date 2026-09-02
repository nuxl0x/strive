package nux.strive.ui.components.overview

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import nux.strive.models.DeadlineModel
import nux.strive.models.DeadlineStatus
import nux.strive.ui.theme.StriveText
import nux.strive.ui.theme.StriveTheme
import kotlin.time.Clock

@Composable
fun DeadlineItem(model: DeadlineModel) {
    val accentColor = when (model.getDeadlineStatus()) {
        DeadlineStatus.OVERDUE -> Color(0xFFAC3636)
        DeadlineStatus.DUE -> Color(0xFFBF5B34)
        DeadlineStatus.SOON -> StriveTheme.colors.accent
        DeadlineStatus.NOT_DUE -> StriveTheme.colors.stroke
    }

    val currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    val isToday = model.dueDate == currentDate
    val day = model.dueDate.dayOfWeek.name
    val month = model.dueDate.month.name
    val dayOfMonth = model.dueDate.day

    val dateText = if (isToday) { "Today" } else { "" }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(61.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(StriveTheme.colors.greyText),
        border = BorderStroke(1.dp, accentColor)
    ) {
        Column {
            Text(
                text = model.taskName,
                color = StriveTheme.colors.text,
                style = StriveText.Normal,
                fontSize = 13.sp,
            )

            Row {
                Text(
                    text = dateText
                )
            }
        }
    }
}

