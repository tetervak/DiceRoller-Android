package ca.tetervak.diceroller.ui.roller

import ca.tetervak.diceroller.domain.HistoryCounts
import ca.tetervak.diceroller.domain.RollData
import java.util.Date

sealed interface RollerUiState {
    object Loading : RollerUiState
    data class Rolled(
        val rollData: RollData, val historyCounts: HistoryCounts, val date: Date
    ) : RollerUiState
    object NotRolled : RollerUiState
}