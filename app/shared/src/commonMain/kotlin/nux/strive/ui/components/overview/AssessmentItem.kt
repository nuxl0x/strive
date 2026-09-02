package nux.strive.ui.components.overview

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.LocalDate
import nux.strive.models.AssessmentModel
import nux.strive.models.Status
import nux.strive.ui.theme.StriveText
import nux.strive.ui.theme.StriveTheme
import nux.strive.util.format

@Composable
fun DeadlineItem(model: AssessmentModel) {
    val accentColor = when (model.getDeadlineStatus()) {
        Status.OVERDUE -> Color(0xFFAC3636)
        Status.DUE -> Color(0xFFBF5B34)
        Status.SOON -> StriveTheme.colors.accent
        Status.NOT_DUE -> StriveTheme.colors.stroke
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(StriveTheme.colors.bg),
        border = BorderStroke(1.dp, accentColor)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Text(
                text = model.name,
                color = StriveTheme.colors.text,
                style = StriveText.Normal,
                fontSize = 13.sp,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = model.dueDate.format(shouldComparativeFormat = true),
                    color = StriveTheme.colors.greyText,
                    style = StriveText.Normal,
                    fontSize = 12.sp,
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = if (model.getDeadlineStatus() == Status.NOT_DUE) {
                        ""
                    } else {
                        model.getDeadlineStatus().name
                    },
                    color = accentColor,
                    style = StriveText.Semibold,
                    fontSize = 11.sp,
                    maxLines = 1
                )
            }
        }
    }
}

val exampleAssessmentModels = listOf(
    AssessmentModel("Math Midterm Exam", LocalDate(2026, 9, 1)),
    AssessmentModel("Philosophy Paper Draft", LocalDate(2026, 9, 2)),
    AssessmentModel("Physics Lab Report 3", LocalDate(2026, 9, 3)),
    AssessmentModel("English Language Exam", LocalDate(2026, 9, 28)),
    AssessmentModel("Applied Computing SAC", LocalDate(2026, 9, 3))
)

