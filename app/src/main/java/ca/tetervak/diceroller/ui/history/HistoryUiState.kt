package ca.tetervak.diceroller.ui.history

import ca.tetervak.diceroller.domain.HistoryCounts

sealed interface HistoryUiState{
    object Loading: HistoryUiState

    data class Loaded(
        val itemStates: List<HistoryItemUiState>,
        val historyCounts: HistoryCounts
    ): HistoryUiState

    object Empty: HistoryUiState
}


