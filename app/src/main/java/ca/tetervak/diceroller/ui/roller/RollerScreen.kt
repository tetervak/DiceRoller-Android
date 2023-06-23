package ca.tetervak.diceroller.ui.roller

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.tetervak.diceroller.R
import ca.tetervak.diceroller.domain.RollData
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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = modifier
                .fillMaxHeight()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(top = 40.dp)
                .fillMaxWidth()
        ) {
            if (rollerUiState is RollerUiState.Rolled) {
                val rollData: RollData = rollerUiState.rollData
                val list: List<Int> = rollData.values
                DiceImagesRow(list)
                DiceValuesRow(list)
                TotalRow(labelRes = R.string.roll_total_label, total = rollData.total)
                TotalRow(
                    labelRes = R.string.history_total_label,
                    total = rollerUiState.historyCounts.historyTotal
                )
            } else {
                TotalRow(labelRes = R.string.roll_total_label, total = 0)
                TotalRow(labelRes = R.string.history_total_label, total = 0)
            }
            Button(
                onClick = { viewModel.onRoll() }, modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(text = stringResource(R.string.roll_button_label))
            }
            if (rollerUiState is RollerUiState.Rolled) {
                Button(
                    onClick = { viewModel.onReset() }, modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text(text = stringResource(R.string.reset_button_label))
                }
            }
        }
    }
}

@Composable
fun TotalRow(@StringRes labelRes: Int, total: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.wrapContentWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(labelRes), fontSize = 34.sp
        )
        Text(
            text = total.toString(), fontSize = 34.sp, color = colorResource(R.color.green_500)
        )
    }
}

@Composable
private fun DiceValuesRow(list: List<Int>, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.wrapContentWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        for (value in list) {
            Text(
                text = value.toString(),
                color = colorResource(R.color.deep_purple_500),
                fontSize = 56.sp
            )
        }
    }
}

@Composable
private fun DiceImagesRow(list: List<Int>, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.wrapContentWidth(),
    ) {
        for (value in list) {
            DiceImage(value)
        }
    }
}

@Composable
private fun DiceImage(value: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(diceImageResourceId(value)),
        contentDescription = value.toString(),
        modifier = modifier.size(80.dp)
    )
}

private fun diceImageResourceId(value: Int) = when (value) {
    1 -> R.drawable.dice_1
    2 -> R.drawable.dice_2
    3 -> R.drawable.dice_3
    4 -> R.drawable.dice_4
    5 -> R.drawable.dice_5
    else -> R.drawable.dice_6
}