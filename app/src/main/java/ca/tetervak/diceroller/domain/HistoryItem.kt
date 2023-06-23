package ca.tetervak.diceroller.domain

import java.util.Date

data class HistoryItem(
    val id: Int = 0,
    val rollData: RollData,
    val date: Date
)
