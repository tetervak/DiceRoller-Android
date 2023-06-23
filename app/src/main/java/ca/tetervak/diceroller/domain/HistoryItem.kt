package ca.tetervak.diceroller.domain

import java.util.Date

data class HistoryItem(
    val id: Int = 0,
    val rollValues: List<Int>,
    val rollTotal: Int,
    val date: Date
)
