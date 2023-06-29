package ca.tetervak.diceroller.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import ca.tetervak.diceroller.ui.common.RollerBottomBar
import ca.tetervak.diceroller.ui.common.RollerTopAppBar
import ca.tetervak.diceroller.ui.navigation.SettingsDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onTabPressed: (String) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
){

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            RollerTopAppBar(
                title = stringResource(SettingsDestination.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack,
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            RollerBottomBar(
                currentRoute = SettingsDestination.route,
                onTabPressed = onTabPressed
            )
        }
    ) { innerPadding ->
        Column(modifier = modifier.padding(innerPadding)){

        }
    }

}