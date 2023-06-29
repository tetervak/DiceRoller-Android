package ca.tetervak.diceroller.data.local.history

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "history_items")
class LocalHistoryItem(

    @PrimaryKey(autoGenerate = true)
    // using the default column name
    val id: Int = 0,

    @ColumnInfo(name = "roll_values")
    val rollValues: String,

    @ColumnInfo(name = "roll_total")
    val rollTotal: Int,

    @ColumnInfo(name = "time_stamp")
    val date: Date
)