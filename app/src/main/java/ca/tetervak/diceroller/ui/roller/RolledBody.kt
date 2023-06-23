package ca.tetervak.diceroller.ui.roller

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.tetervak.diceroller.R
import ca.tetervak.diceroller.domain.HistoryCounts
import ca.tetervak.diceroller.domain.RollData
import ca.tetervak.diceroller.ui.common.DiceImagesRow
import ca.tetervak.diceroller.ui.common.DiceValuesRow
import ca.tetervak.diceroller.ui.common.TotalRow
import ca.tetervak.diceroller.ui.theme.AppTheme
import java.util.Date

@Composable
fun RolledBody(
    rollData: RollData,
    historyCounts: HistoryCounts,
    date: Date,
    onRoll: () -> Unit,
    onReset: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(top = 40.dp)
            .fillMaxWidth()
    ) {
        val list: List<Int> = rollData.values
        DiceImagesRow(list)
        DiceValuesRow(list)
        TotalRow(labelRes = R.string.roll_total_label, total = rollData.total)
        if (historyCounts.historyLength > 1) {
            TotalRow(
                labelRes = R.string.history_total_label, total = historyCounts.historyTotal
            )
        }
        TotalRow(
            labelRes = R.string.roll_count, total = historyCounts.historyLength
        )
        Button(
            onClick = onRoll, modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(text = stringResource(R.string.roll_button_label))
        }
        Button(
            onClick = onReset, modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = stringResource(R.string.reset_button_label))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RolledBodyPreview(){
    AppTheme{
        RolledBody(
            rollData = RollData(listOf(1,2,3)),
            historyCounts = HistoryCounts(
                historyLength = 2,
                historyTotal = 32
            ),
            date = Date(),
            onRoll = {},
            onReset = {}
        )
    }
}