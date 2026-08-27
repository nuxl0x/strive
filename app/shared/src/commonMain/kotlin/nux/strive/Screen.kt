package nux.strive

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.vectorResource
import strive.app.shared.generated.resources.Res
import strive.app.shared.generated.resources.ic_account
import strive.app.shared.generated.resources.ic_detailed
import strive.app.shared.generated.resources.ic_overview
import strive.app.shared.generated.resources.ic_settings

sealed interface Screen {
    val name: String
    val icon: ImageVector @Composable get

    @Serializable
    object Overview : Screen {
        override val name = "Overview"
        override val icon: ImageVector
            @Composable get() = vectorResource(Res.drawable.ic_overview)
    }

    @Serializable
    object Detailed : Screen {
        override val name = "Detailed View"
        override val icon: ImageVector
            @Composable get() = vectorResource(Res.drawable.ic_detailed)
    }

    @Serializable
    object Settings : Screen {
        override val name = "Settings"
        override val icon: ImageVector
            @Composable get() = vectorResource(Res.drawable.ic_settings)
    }

    @Serializable
    object Account : Screen {
        override val name = "Account"
        override val icon: ImageVector
            @Composable get() = vectorResource(Res.drawable.ic_account)
    }

    companion object {
        val sidebarItems = listOf(Overview, Detailed, Settings, Account)
    }
}