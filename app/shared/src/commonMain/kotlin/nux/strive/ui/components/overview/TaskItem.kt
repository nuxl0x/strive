package nux.strive.ui.components.overview

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nux.strive.models.TaskModel
import nux.strive.ui.theme.StriveText
import nux.strive.ui.theme.StriveTheme
import nux.strive.util.format
import org.jetbrains.compose.resources.vectorResource
import strive.app.shared.generated.resources.Res
import strive.app.shared.generated.resources.ic_checkmark
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

@Composable
fun TaskItem(model: TaskModel) {
    var isCrossed by remember { mutableStateOf(false) }
    val textColor = if (isCrossed) {
        StriveTheme.colors.greyText
    } else {
        StriveTheme.colors.text
    }

    val checkMarkBgColor = if (isCrossed) {
        StriveTheme.colors.accent
    } else {
        StriveTheme.colors.bg
    }

    val strokeColor = if (isCrossed) {
        StriveTheme.colors.accent
    } else {
        StriveTheme.colors.stroke
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        // checkbox

        Row{
            Card(
                onClick = { isCrossed = !isCrossed },
                modifier = Modifier.size(20.dp),
                colors = CardDefaults.cardColors(checkMarkBgColor),
                shape = RoundedCornerShape(6.dp),
                border = BorderStroke(1.5.dp, strokeColor),
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = vectorResource(Res.drawable.ic_checkmark),
                        contentDescription = model.name,
                        modifier = Modifier.size(10.dp),
                        tint = StriveTheme.colors.bg
                    )
                }
            }

            Spacer(Modifier.width(16.dp))

            Text(
                text = model.name,
                color = textColor,
                style = StriveText.Normal,
                fontSize = 14.sp,
                textDecoration = if (isCrossed) TextDecoration.LineThrough else TextDecoration.None,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Card(
                modifier = Modifier.height(22.dp),
                shape = RoundedCornerShape(4.dp),
                colors = CardDefaults.cardColors(StriveTheme.colors.selected),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = model.subject.uppercase(),
                        color = StriveTheme.colors.accent,
                        fontSize = 11.sp,
                        style = StriveText.Normal,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = model.duration.format(),
                color = StriveTheme.colors.greyText,
                style = StriveText.Normal,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

    Spacer(Modifier.height(14.dp))

    HorizontalDivider(
        modifier = Modifier.fillMaxWidth(),
        thickness = 1.dp,
        color = StriveTheme.colors.stroke,

    )

    Spacer(Modifier.height(14.dp))
}

val exampleTaskModels = listOf(
    TaskModel(
        "Read & annotate Chapter 4 of Electrodynamics",
        1.5.hours,
        "PHYSICS"
    ),

    TaskModel(
        "Draft outline for Philosophy term paper",
        1.hours,
        "PHIL"
    ),

    TaskModel(
        "Complete problem set on Multivariable Calculus",
        2.hours,
        "MATH"
    ),

    TaskModel(
        "Review active recall cards for Classical Mechanics",
        45.minutes,
        "PHYSICS"
    ),

    TaskModel(
        "Submit initial prototype proposal",
        30.minutes,
        "DESIGN"
    )
)