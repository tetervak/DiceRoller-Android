package ca.tetervak.diceroller.ui.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.tetervak.diceroller.R
import ca.tetervak.diceroller.domain.HistoryCounts
import ca.tetervak.diceroller.domain.HistoryItem
import ca.tetervak.diceroller.domain.RollData
import ca.tetervak.diceroller.ui.common.LoadingBody
import ca.tetervak.diceroller.ui.common.RollerBottomBar
import ca.tetervak.diceroller.ui.common.RollerTopAppBar
import ca.tetervak.diceroller.ui.common.TotalRow
import ca.tetervak.diceroller.ui.common.formatDate
import ca.tetervak.diceroller.ui.common.formatTime
import ca.tetervak.diceroller.ui.navigation.HistoryDestination
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel,
    onTabPressed: (String) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {

    val state: State<HistoryUiState> = viewModel.historyUiState.collectAsState()
    val historyUiState: HistoryUiState = state.value

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            RollerTopAppBar(
                title = stringResource(HistoryDestination.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack,
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            RollerBottomBar(
                currentRoute = HistoryDestination.route,
                onTabPressed = onTabPressed
            )
        }
    ) { innerPadding ->
        when(historyUiState){
            is HistoryUiState.Empty -> EmptyHistoryBody(
                modifier = modifier.padding(innerPadding)
            )
            is HistoryUiState.Loading -> LoadingBody(
                modifier = modifier.padding(innerPadding)
            )
            is HistoryUiState.Loaded -> HistoryDataBody(
                historyUiState = historyUiState,
                modifier = modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
private fun HistoryDataBody(
    historyUiState: HistoryUiState.Loaded,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        HistoryList(
            itemStates = historyUiState.itemStates,
            modifier = Modifier.fillMaxWidth().weight(1f)
        )
        Divider(
            color = Color.Gray, thickness = 2.dp
        )
        HistorySummary(
            historyCounts = historyUiState.historyCounts,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun HistorySummary(
    historyCounts: HistoryCounts,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.End,
        modifier = modifier.padding(8.dp)
    ){
        TotalRow(
            labelRes = R.string.history_total_label, total = historyCounts.historyTotal
        )
        TotalRow(
            labelRes = R.string.roll_count, total = historyCounts.historyLength
        )
    }
}

@Composable
fun HistoryList(
    itemStates: List<HistoryItemUiState>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(
            vertical = 8.dp, horizontal = 8.dp
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ){
        items(itemStates){ itemState ->
            HistoryListItem(itemState)
        }
    }
}

@Composable
fun HistoryListItem(itemState: HistoryItemUiState, modifier: Modifier = Modifier) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = modifier
            .fillMaxWidth(0.8f)
            .padding(8.dp)
    ){
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier.padding(8.dp)
        ){
            Text(
                text = "${itemState.count}.",
                fontSize = 18.sp,
                color = colorResource(id = R.color.purple_700)
            )
            Column {
                HistoryItemNumberOfDice(number = itemState.historyItem.rollData.numberOfDice)
                HistoryItemDiceValues(rollData = itemState.historyItem.rollData)
                HistoryItemDate(date = itemState.historyItem.date)
                HistoryItemTime(date = itemState.historyItem.date)
            }
        }
    }
}

@Preview
@Composable
fun HistoryListItemPreview(){
    val itemUiState =
        HistoryItemUiState(
            count = 3,
            historyItem = HistoryItem(
                rollData = RollData(values = listOf(4,6,2,1)),
                date = Date()
            )
        )
    HistoryListItem(itemState = itemUiState, modifier = Modifier.padding(16.dp))
}

@Composable
fun HistoryItemDiceValues(rollData: RollData, modifier: Modifier = Modifier) {
    if(rollData.numberOfDice > 1){
        Row(modifier = modifier){
            Text(
                text = stringResource(id = R.string.values),
                fontSize = 18.sp
            )
            Text(
                text = rollData.values.joinToString(separator = " + "),
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 4.dp)
            )
            Text(
                text = " = ",
                fontSize = 18.sp
            )
            Text(
                text = rollData.total.toString(),
                fontSize = 18.sp,
                color = colorResource(id = R.color.green_500)
            )
        }
    } else {
        Row(modifier = modifier) {
            Text(text = stringResource(id = R.string.value))
            Text(text = rollData.values.first().toString())
        }
    }
}

@Composable
fun HistoryItemNumberOfDice(number: Int, modifier: Modifier = Modifier) {
    Row (
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
    ){
        Text(
            text = stringResource(id = R.string.number_of_dice),
            fontSize = 18.sp
        )
        Text(
            text = number.toString(),
            fontSize = 18.sp
        )
    }
}

@Composable
fun HistoryItemDate(date: Date, modifier: Modifier = Modifier){
    Text(
        text = formatDate(date),
        fontSize = 16.sp,
        color = Color.Gray,
        modifier = modifier
    )
}

@Composable
fun HistoryItemTime(date: Date, modifier: Modifier = Modifier){
    Text(
        text = formatTime(date),
        fontSize = 16.sp,
        color = Color.Gray,
        modifier = modifier
    )
}
