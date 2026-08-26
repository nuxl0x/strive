package nux.strive.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf

val LocalStriveColours = staticCompositionLocalOf<StrivePalette> { DarkColors }

object StriveTheme {
    val colors: StrivePalette @Composable get() = LocalStriveColours.current
}