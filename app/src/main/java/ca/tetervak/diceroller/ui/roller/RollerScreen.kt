package ca.tetervak.diceroller.ui.roller

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import ca.tetervak.diceroller.ui.common.LoadingBody
import ca.tetervak.diceroller.ui.common.RollerBottomBar
import ca.tetervak.diceroller.ui.common.RollerTopAppBar
import ca.tetervak.diceroller.ui.navigation.RollerDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RollerScreen(
    viewModel: RollerViewModel,
    onTabPressed: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    val state: State<RollerUiState> = viewModel.rollerUiState
    val rollerUiState: RollerUiState = state.value
    val numberOfDice: Int by viewModel.numberOfDice.collectAsState()

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            RollerTopAppBar(
                title = stringResource(RollerDestination.titleRes),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            RollerBottomBar(
                currentRoute = RollerDestination.route,
                onTabPressed = onTabPressed
            )
        }
    ) { innerPadding ->
        when (rollerUiState) {
            is RollerUiState.Rolled -> RolledBody(
                rollData = rollerUiState.rollData,
                historyCounts = rollerUiState.historyCounts,
                date = rollerUiState.date,
                numberOfDice = numberOfDice,
                onRoll = viewModel::onRoll,
                onReset = viewModel::onReset,
                modifier = modifier.padding(innerPadding)
            )
            is RollerUiState.NotRolled -> NotRolledBody(
                numberOfDice = numberOfDice,
                onRoll = viewModel::onRoll,
                modifier = modifier.padding(innerPadding)
            )
            is RollerUiState.Loading -> LoadingBody(
                modifier = modifier.padding(innerPadding)
            )
        }
    }
}