package ca.tetervak.diceroller.ui.roller

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.tetervak.diceroller.R
import ca.tetervak.diceroller.domain.RollData

@Composable
fun RollerScreen() {

    val viewModel: RollerViewModel = viewModel()

    val state: State<RollerUiState> = viewModel.rollerUiState
    val rollerUiState: RollerUiState = state.value

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.roll_dice),
            color = colorResource(R.color.pink_500),
            fontSize = 34.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
        if(rollerUiState is RollerUiState.Rolled){
            val rollData: RollData = rollerUiState.rollData
            val list: List<Int> = rollData.values
            DiceImagesRow(list)
            DiceValuesRow(list)
            TotalRow(total = rollData.total)
        } else{
            TotalRow(total = 0)
        }
        Button(
            onClick = { viewModel.onRoll() },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(text = stringResource(R.string.roll_button_label))
        }
        if(rollerUiState is RollerUiState.Rolled){
            Button(
                onClick = { viewModel.onReset() },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = stringResource(R.string.reset_button_label))
            }
        }
    }
}

@Composable
private fun TotalRow(total: Int) {
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.total_label),
            fontSize = 34.sp
        )
        Text(
            text = total.toString(),
            fontSize = 34.sp,
            color = colorResource(R.color.green_500)
        )
    }
}

@Composable
private fun DiceValuesRow(list: List<Int>) {
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
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
private fun DiceImagesRow(list: List<Int>) {
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .padding(top = 16.dp)
    ) {
        for (value in list) {
            DiceImage(value)
        }
    }
}

@Composable
private fun DiceImage(value: Int) {
    Image(
        painter = painterResource(diceImageResourceId(value)),
        contentDescription = value.toString(),
        modifier = Modifier.size(80.dp)
    )
}

private fun diceImageResourceId(value: Int) =
    when (value) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

//@Preview
//@Composable
//fun DiceRollerScreenRolledPreview() {
//    AppTheme {
//        DiceRollerScreen(
//            viewModel = MainViewModel(
//                game = DiceGame().apply { roll() }
//            )
//        )
//    }
//}

//@Preview
//@Composable
//fun DiceRollerScreenNotRolledPreview() {
//    AppTheme {
//        DiceRollerScreen(
//            viewModel = MainViewModel(game = DiceGame())
//        )
//    }
//}