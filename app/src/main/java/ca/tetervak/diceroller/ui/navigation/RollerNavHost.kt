package ca.tetervak.diceroller.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ca.tetervak.diceroller.ui.history.HistoryScreen
import ca.tetervak.diceroller.ui.history.HistoryViewModel
import ca.tetervak.diceroller.ui.roller.RollerScreen
import ca.tetervak.diceroller.ui.roller.RollerViewModel
import ca.tetervak.diceroller.ui.settings.SettingsScreen

@Composable
fun RollerNavHost(navController: NavHostController){
    NavHost(navController, startDestination = RollerDestination.route){
        composable(route = RollerDestination.route){
            val viewModel: RollerViewModel = hiltViewModel()
            RollerScreen(
                viewModel = viewModel,
                onTabPressed = tabNavigate(navController)
            )
        }
        composable(route = HistoryDestination.route){
            val viewModel: HistoryViewModel = hiltViewModel()
            HistoryScreen(
                viewModel = viewModel,
                onTabPressed = tabNavigate(navController),
                navigateBack = { navController.popBackStack() }
            )
        }
        composable(route = SettingsDestination.route){
            SettingsScreen(
                onTabPressed = tabNavigate(navController),
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}

@Composable
private fun tabNavigate(navController: NavHostController): (String) -> Unit = { route: String ->
    if (route == RollerDestination.route) {
        navController.popBackStack(
            route = RollerDestination.route, inclusive = false
        )
    } else {
        navController.navigate(route)
    }
}