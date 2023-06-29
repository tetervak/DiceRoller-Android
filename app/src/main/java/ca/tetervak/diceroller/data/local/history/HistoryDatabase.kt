package ca.tetervak.diceroller.data.local.history

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [LocalHistoryItem::class], version = 1, exportSchema = false)
@TypeConverters(DataConverters::class)
abstract class HistoryDatabase : RoomDatabase() {

    abstract fun historyItemDao(): HistoryItemDao
}