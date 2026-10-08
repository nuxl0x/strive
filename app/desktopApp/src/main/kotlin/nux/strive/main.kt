package nux.strive

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
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
        App(httpClient)
    }
}

val httpClient = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            isLenient = true
        })
    }
    defaultRequest {
        host = "localhost"
        port = 8087
    }
}