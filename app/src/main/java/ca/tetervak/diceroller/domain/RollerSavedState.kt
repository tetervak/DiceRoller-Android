package ca.tetervak.diceroller.domain

sealed interface RollerSavedState {
    object NotSaved : RollerSavedState
    data class Saved(
        val historyItem: HistoryItem, val historyCounts: HistoryCounts
    ) : RollerSavedState
}