package ca.tetervak.diceroller.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import ca.tetervak.diceroller.R
import ca.tetervak.diceroller.ui.navigation.HistoryDestination
import ca.tetervak.diceroller.ui.navigation.RollerDestination
import ca.tetervak.diceroller.ui.navigation.SettingsDestination

private data class NavigationItemContent(
    val route: String,
    val icon: ImageVector,
    val text: String
)

@Composable
fun RollerBottomBar(
    currentRoute: String,
    onTabPressed: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val navigationItemContentList = listOf(
        NavigationItemContent(
            route = RollerDestination.route,
            icon = Icons.Default.Home,
            text = stringResource(id = R.string.roller)
        ),
        NavigationItemContent(
            route = HistoryDestination.route,
            icon = Icons.Default.List,
            text = stringResource(id = R.string.history)
        ),
        NavigationItemContent(
            route = SettingsDestination.route,
            icon = Icons.Default.Settings,
            text = stringResource(id = R.string.settings)
        )
    )

    NavigationBar(modifier = modifier) {
        for (navItem in navigationItemContentList) {
            NavigationBarItem(
                selected = currentRoute == navItem.route,
                onClick = { onTabPressed(navItem.route) },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.text
                    )
                },
                label = { Text(text = navItem.text) }
            )
        }
    }
}

