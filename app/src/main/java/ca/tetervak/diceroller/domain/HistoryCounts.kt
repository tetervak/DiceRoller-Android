package ca.tetervak.diceroller.domain

data class HistoryCounts(
    val historyLength: Int, val historyTotal: Int
)

fun HistoryCounts.toUpdatedBy(rollTotal: Int): HistoryCounts = HistoryCounts(
    historyLength = this.historyLength + 1, historyTotal = this.historyTotal + rollTotal
)