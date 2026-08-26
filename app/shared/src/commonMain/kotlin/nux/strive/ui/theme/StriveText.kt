package nux.strive.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import strive.app.shared.generated.resources.Res
import strive.app.shared.generated.resources.geist_bold
import strive.app.shared.generated.resources.geist_regular
import strive.app.shared.generated.resources.geist_semibold

object StriveText {
    private val family: FontFamily
        @Composable get() = FontFamily(
            Font(resource = Res.font.geist_regular, weight = FontWeight.Normal),
            Font(resource = Res.font.geist_semibold, weight = FontWeight.SemiBold),
            Font(resource = Res.font.geist_bold, weight = FontWeight.Bold)
        )

    // Direct, manual text styles ready for your views
    val Bold: TextStyle
        @Composable get() = TextStyle(fontFamily = family, fontWeight = FontWeight.Bold, fontSize = 20.sp)

    val Semibold: TextStyle
        @Composable get() = TextStyle(fontFamily = family, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)

    val Normal: TextStyle
        @Composable get() = TextStyle(fontFamily = family, fontWeight = FontWeight.Normal, fontSize = 14.sp)
}