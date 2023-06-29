package ca.tetervak.diceroller.ui.roller

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.tetervak.diceroller.R
import ca.tetervak.diceroller.ui.common.TotalRow
import ca.tetervak.diceroller.ui.theme.AppTheme

@Composable
fun NotRolledBody(
    numberOfDice: Int,
    onRoll: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = modifier.fillMaxWidth().padding(top = 40.dp)
    ) {
        TotalRow(labelRes = R.string.roll_count, total = 0)
        Button(
            onClick = onRoll,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(text = stringResource(R.string.roll_button_label, numberOfDice))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotRolledBodyPreview(){
    AppTheme {
        NotRolledBody(numberOfDice = 3, onRoll = {})
    }
}