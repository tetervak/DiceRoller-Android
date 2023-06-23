package ca.tetervak.diceroller.ui.roller

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.tetervak.diceroller.ui.common.RollerTopAppBar
import ca.tetervak.diceroller.ui.navigation.RollerDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RollerScreen(modifier: Modifier = Modifier) {

    val viewModel: RollerViewModel = viewModel()

    val state: State<RollerUiState> = viewModel.rollerUiState
    val rollerUiState: RollerUiState = state.value

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection), topBar = {
        RollerTopAppBar(
            title = stringResource(RollerDestination.titleRes),
            canNavigateBack = false,
            scrollBehavior = scrollBehavior
        )
    }) { innerPadding ->
        when (rollerUiState) {
            is RollerUiState.Rolled -> RolledBody(
                rollData = rollerUiState.rollData,
                historyCounts = rollerUiState.historyCounts,
                date = rollerUiState.date,
                onRoll = viewModel::onRoll,
                onReset = viewModel::onReset,
                modifier = modifier.padding(innerPadding)
            )
            is RollerUiState.NotRolled -> NotRolledBody(
                onRoll = viewModel::onRoll, modifier = modifier.padding(innerPadding)
            )
            is RollerUiState.Loading -> LoadingBody(
                modifier = modifier.padding(innerPadding)
            )
        }
    }
}