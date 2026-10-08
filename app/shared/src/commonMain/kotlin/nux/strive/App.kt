package nux.strive

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.ktor.client.HttpClient
import nux.strive.ui.components.Sidebar
import nux.strive.ui.theme.DarkColors
import nux.strive.ui.theme.LocalStriveColours
import nux.strive.ui.theme.StriveText
import nux.strive.ui.theme.StriveTheme
import nux.strive.vmodels.OverviewViewModel
import nux.strive.vmodels.TeacherViewModel

@Composable
fun App(httpClient: HttpClient) {
    val teacherMode = false
    val targetStudentUuid = "student-1-uuid"

    val overviewViewModel = remember(httpClient) {
        OverviewViewModel(httpClient, targetStudentUuid)
    }

    val teacherViewModel = remember(httpClient) {
        TeacherViewModel(httpClient)
    }

    val currentPalette = DarkColors

    val navController = rememberNavController()

    CompositionLocalProvider(LocalStriveColours provides currentPalette) {
        if (teacherMode) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(StriveTheme.colors.bg)
            ) {
                TeacherView(teacherViewModel)
            }
        } else {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(StriveTheme.colors.bg)
            ) {
                Sidebar(navController)

                NavHost(
                    navController = navController,
                    startDestination = Screen.Overview,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(45.dp, 40.dp),
                ) {
                    composable<Screen.Overview> { Overview(overviewViewModel) }
                    composable<Screen.Detailed> {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                text = "Details screen!",
                                color = StriveTheme.colors.text,
                                style = StriveText.Normal,
                                fontSize = 24.sp
                            )
                        }
                    }
                    composable<Screen.Settings> {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                text = "Settings screen!",
                                color = StriveTheme.colors.text,
                                style = StriveText.Normal,
                                fontSize = 24.sp
                            )
                        }
                    }
                    composable<Screen.Account> {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                text = "Account screen!",
                                color = StriveTheme.colors.text,
                                style = StriveText.Normal,
                                fontSize = 24.sp
                            )
                        }
                    }
                }
            }
        }
    }
}