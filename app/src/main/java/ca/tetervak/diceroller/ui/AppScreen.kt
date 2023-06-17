package ca.tetervak.diceroller.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ca.tetervak.diceroller.ui.roller.RollerScreen

@Composable
fun AppScreen(modifier: Modifier = Modifier) {
    RollerScreen()
}

//@Preview(showBackground = true)
//@Composable
//fun AppScreenPreview() {
//    AppTheme {
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//            color = MaterialTheme.colorScheme.background
//        ) {
//            AppScreen()
//        }
//    }
//}