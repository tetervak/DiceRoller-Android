package ca.tetervak.diceroller.domain

import androidx.room.ColumnInfo

data class LocalHistory(

    @ColumnInfo(name = "history_length")
    val historyLength: Int,

    @ColumnInfo(name = "history_total")
    val historyTotal: Int
)
