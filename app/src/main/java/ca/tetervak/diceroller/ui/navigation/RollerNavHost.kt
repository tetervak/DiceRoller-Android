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
import ca.tetervak.diceroller.ui.settings.SettingsViewModel

@Composable
fun RollerNavHost(navController: NavHostController){
    NavHost(navController, startDestination = RollerDestination.route){
        composable(route = RollerDestination.route){
            val viewModel: RollerViewModel = hiltViewModel()
            RollerScreen(
                viewModel = viewModel,
                onTabPressed = { tabNavigate(navController, it) }
            )
        }
        composable(route = HistoryDestination.route){
            val viewModel: HistoryViewModel = hiltViewModel()
            HistoryScreen(
                viewModel = viewModel,
                onTabPressed = { tabNavigate(navController, it) },
                navigateBack = { navController.popBackStack() }
            )
        }
        composable(route = SettingsDestination.route){
            val viewModel: SettingsViewModel = hiltViewModel()
            SettingsScreen(
                viewModel = viewModel,
                onTabPressed = { tabNavigate(navController, it) },
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}

private fun tabNavigate(navController: NavHostController, route: String){
    if (route == RollerDestination.route) {
        // pop the stack when returning to the home screen
        navController.popBackStack(
            route = RollerDestination.route, inclusive = false
        )
    } else {
        navController.navigate(route)
    }
}