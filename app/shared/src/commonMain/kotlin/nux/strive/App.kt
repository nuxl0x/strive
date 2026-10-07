package nux.strive

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.ktor.client.HttpClient
import nux.strive.ui.components.Sidebar
import nux.strive.ui.theme.DarkColors
import nux.strive.ui.theme.LocalStriveColours
import nux.strive.ui.theme.StriveTheme
import nux.strive.vmodels.OverviewViewModel

@Composable
fun App(httpClient: HttpClient) {
    val overviewViewModel = remember(httpClient) {
        OverviewViewModel(httpClient)
    }
    var isDarkMode by remember { mutableStateOf(true) }
    val currentPalette = DarkColors

    val navController = rememberNavController()

    CompositionLocalProvider(LocalStriveColours provides currentPalette) {
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
                composable<Screen.Detailed> { println("DETAILS") }
                composable<Screen.Settings> { println("SETTINGS") }
                composable<Screen.Account> { println("ACCOUNT") }
            }
        }
    }
}