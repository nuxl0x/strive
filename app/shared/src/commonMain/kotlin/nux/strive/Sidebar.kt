package nux.strive

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nux.strive.ui.components.SidebarItem
import nux.strive.ui.theme.StriveText
import nux.strive.ui.theme.StriveTheme

@Composable
fun Sidebar() {
    val strokeColor = StriveTheme.colors.stroke
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(240.dp)
            .background(color = StriveTheme.colors.surface)
            .drawWithContent {
                drawContent()

                drawLine(
                    color = strokeColor,
                    start = Offset(x = size.width - 0.5f, y = 0f),
                    end = Offset(x = size.width - 0.5f, y = size.height),
                    strokeWidth = 1f
                )
            }
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = "strive/overview",
            color = StriveTheme.colors.text,
            style = StriveText.Bold
        )

        Screen.sidebarItems.forEach { item ->
            SidebarItem(item)
        }
    }
}