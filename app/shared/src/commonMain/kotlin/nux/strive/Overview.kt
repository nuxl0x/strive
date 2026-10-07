package nux.strive

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import nux.strive.ui.components.overview.CustomTaskDialog
import nux.strive.ui.components.overview.DeadlineItem
import nux.strive.ui.components.overview.TaskItem
import nux.strive.ui.components.overview.exampleAssessmentModels
import nux.strive.ui.components.overview.exampleTaskModels
import nux.strive.ui.theme.StriveText
import nux.strive.ui.theme.StriveTheme
import nux.strive.util.format
import nux.strive.vmodels.OverviewUiState
import nux.strive.vmodels.OverviewViewModel
import kotlin.time.Clock

@Composable
fun Overview(viewModel: OverviewViewModel) {
    val textInteractionSource = remember { MutableInteractionSource() }
    val currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    val listState = rememberLazyListState()
    var shouldShowTaskDialog by remember { mutableStateOf(false) }
    val isCustomTaskTextHighlighted by textInteractionSource.collectIsHoveredAsState()

    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // First
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Today's Overview",
                    style = StriveText.Semibold,
                    fontSize = 24.sp,
                    color = StriveTheme.colors.text
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = currentDate.format(),
                    style = StriveText.Normal,
                    fontSize = 13.sp,
                    color = StriveTheme.colors.greyText
                )
            }

            Card(
                modifier = Modifier.size(87.dp, 33.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(StriveTheme.colors.surface),
                border = BorderStroke(1.dp, StriveTheme.colors.stroke)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "25:00",
                        style = StriveText.Semibold,
                        fontSize = 13.sp,
                        color = StriveTheme.colors.text
                    )
                }
            }

        }


        Spacer(modifier = Modifier.height(30.dp))


        // Second Row
        Card(
            modifier = Modifier
                .height(112.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(StriveTheme.colors.surface),
            border = BorderStroke(1.dp, StriveTheme.colors.stroke)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Daily Flow",
                            style = StriveText.Semibold,
                            fontSize = 16.sp,
                            color = StriveTheme.colors.text
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Keep maintaining the pace. 3 out of 5 objectives completed today.",
                            style = StriveText.Normal,
                            fontSize = 13.sp,
                            color = StriveTheme.colors.greyText
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = "60%",
                        style = StriveText.Semibold,
                        fontSize = 20.sp,
                        color = StriveTheme.colors.accent
                    )
                }

                LinearProgressIndicator(
                    progress = { 0.6f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    color = StriveTheme.colors.accent,
                    trackColor = StriveTheme.colors.bg,
                    drawStopIndicator = {}
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))


        // Third Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Card(
                modifier = Modifier
                    .height(334.dp)
                    .weight(2f),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(StriveTheme.colors.surface),
                border = BorderStroke(1.dp, StriveTheme.colors.stroke)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = "Task List",
                            style = StriveText.Semibold,
                            fontSize = 15.sp,
                            color = StriveTheme.colors.text
                        )

                        Text(
                            text = "+ Add Custom Task",
                            style = StriveText.Normal,
                            fontSize = 12.sp,
                            color = StriveTheme.colors.accent,
                            textDecoration = if (isCustomTaskTextHighlighted) TextDecoration.Underline else TextDecoration.None,
                            modifier = Modifier
                                .hoverable(textInteractionSource)
                                .clickable(interactionSource = textInteractionSource
                            ) { shouldShowTaskDialog = true }
                        )

                        if (shouldShowTaskDialog) {
                            CustomTaskDialog { shouldShowTaskDialog = false }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // List
                    exampleTaskModels.forEach { model ->
                        TaskItem(model)
                    }
                }
            }

            Spacer(modifier = Modifier.width(24.dp))

            // Side Stuff
            Column(
                modifier = Modifier.weight(1f).fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Card(
                    modifier = Modifier
                        .weight(0.7f, fill = false)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(StriveTheme.colors.surface),
                    border = BorderStroke(1.dp, StriveTheme.colors.stroke)
                ) {

                    Column(
                        modifier = Modifier
                            .padding(24.dp)
                            .wrapContentHeight()
                    ) {
                        Text(
                            text = "Upcoming Deadlines",
                            style = StriveText.Semibold,
                            fontSize = 14.sp,
                            color = StriveTheme.colors.text
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        LazyColumn(
                            modifier = Modifier.weight(1f, fill = false),
                            state = listState
                        ) {
                            items(exampleAssessmentModels) { model ->
                                DeadlineItem(model)

                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }

                }

                Card(
                    modifier = Modifier
                        .requiredHeightIn(min = 120.dp, max = 120.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(StriveTheme.colors.surface),
                    border = BorderStroke(1.dp, StriveTheme.colors.stroke)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Focus Time Spent",
                            style = StriveText.Semibold,
                            fontSize = 14.sp,
                            color = StriveTheme.colors.text
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(52.dp),
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "TODAY",
                                    style = StriveText.Normal,
                                    fontSize = 11.sp,
                                    color = StriveTheme.colors.greyText,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Text(
                                    text = "3.5h",
                                    style = StriveText.Semibold,
                                    fontSize = 20.sp,
                                    color = StriveTheme.colors.text,
                                    maxLines = 1
                                )
                            }

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "THIS WEEK",
                                    style = StriveText.Normal,
                                    fontSize = 11.sp,
                                    color = StriveTheme.colors.greyText,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Text(
                                    text = "24.2h",
                                    style = StriveText.Semibold,
                                    fontSize = 20.sp,
                                    color = StriveTheme.colors.text,
                                    maxLines = 1
                                )
                            }
                        }
                    }
                }

            }

        }
    }
}

