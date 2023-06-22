package ca.tetervak.diceroller.ui.roller

import ca.tetervak.diceroller.domain.RollData

sealed interface RollerUiState {
    data class Rolled(val rollData: RollData) : RollerUiState
    object NotRolled : RollerUiState
}