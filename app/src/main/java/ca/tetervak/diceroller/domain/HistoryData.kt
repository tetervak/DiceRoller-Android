package ca.tetervak.diceroller.domain

data class HistoryData(
    val historyItems: List<HistoryItem>, val historyCounts: HistoryCounts
)
