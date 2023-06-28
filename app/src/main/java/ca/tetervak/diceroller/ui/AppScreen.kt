package ca.tetervak.diceroller.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ca.tetervak.diceroller.ui.navigation.RollerNavHost

@Composable
fun AppScreen(navController: NavHostController = rememberNavController()) {
    RollerNavHost(navController = navController)
}