package nux.strive

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import nux.strive.ui.components.overview.DeadlineItem
import nux.strive.ui.components.overview.TeacherTaskItem
import nux.strive.ui.theme.StriveText
import nux.strive.ui.theme.StriveTheme
import nux.strive.util.format
import nux.strive.vmodels.TeacherViewModel
import nux.strive.vmodels.TeacherViewUiState
import kotlin.time.Clock

@Composable
fun TeacherView(viewModel: TeacherViewModel) {
    val currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    val listState1 = rememberLazyListState()
    val listState2 = rememberLazyListState()

    val state by viewModel.uiState.collectAsState()

    when (val currentState = state) {
        is TeacherViewUiState.Loading -> {
            CircularProgressIndicator()
        }
        is TeacherViewUiState.Error -> {
            Text(
                text = "Something went wrong!",
                color = StriveTheme.colors.text,
                style = StriveText.Normal,
                fontSize = 24.sp
            )
        }
        is TeacherViewUiState.Success -> {
            val data = currentState.data
            val student1 = data["student-1-uuid"] ?: return
            val student2 = data["student-2-uuid"] ?: return

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
            ) {
                // First
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Teacher Overview",
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
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Second
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                ) {
                    Card(
                        modifier = Modifier.fillMaxSize(),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(StriveTheme.colors.surface),
                        border = BorderStroke(1.dp, StriveTheme.colors.stroke)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(24.dp),
                        ) {
                            Text(
                                text = "Student 1",
                                style = StriveText.Semibold,
                                fontSize = 20.sp,
                                color = StriveTheme.colors.text
                            )

                            Row(
                                modifier = Modifier
                                    .padding(horizontal = 6.dp, vertical = 12.dp),
                            ) {
                                Column(
                                    modifier = Modifier.weight(1f),
                                ) {
                                    student1.tasks.forEach { model ->
                                        TeacherTaskItem(model)
                                    }
                                }

                                Spacer(modifier = Modifier.width(24.dp))

                                LazyColumn(
                                    modifier = Modifier.weight(1f, fill = false),
                                    state = listState1,
                                ) {
                                    items(student1.assessments) { model ->
                                        DeadlineItem(model)

                                        Spacer(modifier = Modifier.height(8.dp))
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Third
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                ) {
                    Card(
                        modifier = Modifier.fillMaxSize(),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(StriveTheme.colors.surface),
                        border = BorderStroke(1.dp, StriveTheme.colors.stroke)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(24.dp),
                        ) {
                            Text(
                                text = "Student 2",
                                style = StriveText.Semibold,
                                fontSize = 20.sp,
                                color = StriveTheme.colors.text
                            )

                            Row(
                                modifier = Modifier
                                    .padding(horizontal = 6.dp, vertical = 12.dp),
                            ) {
                                Column(
                                    modifier = Modifier.weight(1f),
                                ) {
                                    student2.tasks.forEach { model ->
                                        TeacherTaskItem(model)
                                    }
                                }

                                Spacer(modifier = Modifier.width(24.dp))

                                LazyColumn(
                                    modifier = Modifier.weight(1f, fill = false),
                                    state = listState2,
                                ) {
                                    items(student2.assessments) { model ->
                                        DeadlineItem(model)

                                        Spacer(modifier = Modifier.height(8.dp))
                                    }
                                }
                            }
                        }
                    }
                }

            }

        }

    }

}