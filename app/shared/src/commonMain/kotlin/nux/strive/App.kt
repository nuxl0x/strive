package nux.strive

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nux.strive.ui.theme.DarkColors
import nux.strive.ui.theme.LocalStriveColours
import nux.strive.ui.theme.StriveText
import nux.strive.ui.theme.StriveTheme

@Composable
fun App() {
    var isDarkMode by remember { mutableStateOf(true) }
    val currentPalette = DarkColors
    CompositionLocalProvider(LocalStriveColours provides currentPalette) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(StriveTheme.colors.bg)
        ) {
            Sidebar()


        }
    }
}