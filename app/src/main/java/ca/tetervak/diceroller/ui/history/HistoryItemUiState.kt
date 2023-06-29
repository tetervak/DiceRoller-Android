package ca.tetervak.diceroller.ui.history

import ca.tetervak.diceroller.domain.HistoryItem

data class HistoryItemUiState(
    val count: Int,
    val historyItem: HistoryItem
)
