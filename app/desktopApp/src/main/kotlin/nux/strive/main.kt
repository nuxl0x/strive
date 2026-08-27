package nux.strive

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import java.awt.Dimension

fun main() = application {
    val windowState = rememberWindowState(
        width = 1000.dp,
        height = 700.dp
    )

    Window(
        onCloseRequest = ::exitApplication,
        title = "Strive",
        state = windowState,
    ) {
        window.minimumSize = Dimension(1000, 700)
        App()
    }
}