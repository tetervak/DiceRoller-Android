package ca.tetervak.diceroller.data.local

import androidx.room.ColumnInfo

data class LocalHistoryCounts(

    @ColumnInfo(name = "history_length")
    val historyLength: Int,

    @ColumnInfo(name = "history_total")
    val historyTotal: Int?
)
