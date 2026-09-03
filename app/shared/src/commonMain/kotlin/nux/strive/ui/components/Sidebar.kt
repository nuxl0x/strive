package nux.strive.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
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
import androidx.navigation.NavController
import nux.strive.Screen
import nux.strive.ui.theme.StriveText
import nux.strive.ui.theme.StriveTheme

@Composable
fun Sidebar(navController: NavController) {
    val strokeColor = StriveTheme.colors.stroke
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(204.dp)
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
            style = StriveText.Bold,
            fontSize = 16.sp,
        )

        Spacer(modifier = Modifier.height(30.dp))

        Screen.sidebarItems.forEach { item ->
            SidebarItem(item, navController)
        }
    }
}