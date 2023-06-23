package ca.tetervak.diceroller.ui.roller

import ca.tetervak.diceroller.domain.RollData
import java.util.Date

sealed interface RollerUiState {
    data class Rolled(val rollData: RollData, val historyTotal: Int, val date: Date) : RollerUiState
    object NotRolled : RollerUiState
}