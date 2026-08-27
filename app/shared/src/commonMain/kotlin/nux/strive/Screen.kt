package nux.strive

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.vectorResource
import strive.app.shared.generated.resources.Res

sealed interface Screen {
    val name: String
    val icon: ImageVector

    object Home : Screen {
        override val name = "Home"
        override val icon: ImageVector
            @Composable get() = vectorResource(Res.drawable.)
    }
}