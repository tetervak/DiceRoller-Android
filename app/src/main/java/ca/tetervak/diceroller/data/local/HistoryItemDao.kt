package ca.tetervak.diceroller.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryItemDao {

    @Query("SELECT * FROM history_items")
    fun getAllHistoryItemsFlow(): Flow<List<LocalHistoryItem>>

    @Query("SELECT * FROM history_items WHERE id=:id")
    suspend fun getHistoryItemById(id: Int): LocalHistoryItem

    @Query("SELECT SUM(roll_total) FROM history_items")
    suspend fun getHistoryTotal(): Int

    @Query("SELECT COUNT(id) AS history_length, SUM(roll_total) AS history_total FROM history_items")
    suspend fun getHistoryCounts(): LocalHistoryCounts

    @Query("SELECT COUNT(id) AS history_length, SUM(roll_total) AS history_total " +
            "FROM history_items WHERE id<=:id")
    suspend fun getHistoryCountsUntilId(id: Int): LocalHistoryCounts

    @Query("SELECT EXISTS(SELECT id FROM history_items LIMIT 1)")
    suspend fun isHistoryNotEmpty(): Boolean

    @Query("SELECT MAX(id) FROM history_items")
    suspend fun getMaxId(): Int?

    @Transaction
    suspend fun getLastHistoryItem(): LocalHistoryItem? {
        return getMaxId()?.let { getHistoryItemById(it) }
    }

    @Insert
    suspend fun insertHistoryItem(historyItem: LocalHistoryItem)

    @Query("DELETE FROM history_items WHERE id=:id")
    suspend fun deleteHistoryItemById(id: Int)

    @Query("DELETE FROM history_items")
    suspend fun deleteAllHistoryItems()
}