package nux.strive.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import nux.strive.Screen
import nux.strive.ui.theme.StriveText
import nux.strive.ui.theme.StriveTheme

@Composable
fun SidebarItem(item: Screen, navController: NavController) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val isSelected = currentBackStackEntry?.destination?.hasRoute(item::class) ?: false

    Card(
        onClick = { item.navigate(navController) },
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            if (isSelected) StriveTheme.colors.selected else StriveTheme.colors.surface
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = item.name,
                tint = if (isSelected) StriveTheme.colors.textSelected else StriveTheme.colors.greyText
            )
            Text(
                text = item.name,
                style = StriveText.Normal,
                color = if (isSelected) StriveTheme.colors.textSelected else StriveTheme.colors.text
            )
        }
    }
}