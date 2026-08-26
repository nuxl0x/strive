package nux.strive

import androidx.compose.ui.graphics.vector.ImageVector

sealed interface Screen {
    val name: String
    val icon: ImageVector

}