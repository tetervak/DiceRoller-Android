package ca.tetervak.diceroller.data.repository

import ca.tetervak.diceroller.data.local.LocalHistoryCounts
import ca.tetervak.diceroller.domain.HistoryCounts
import ca.tetervak.diceroller.domain.HistoryItem
import kotlinx.coroutines.flow.Flow

interface HistoryItemRepository {

    fun getAllHistoryItemsFlow(): Flow<List<HistoryItem>>

    suspend fun getHistoryItemById(id: Int): HistoryItem

    suspend fun getHistoryTotal(): Int

    suspend fun getHistoryCounts(): HistoryCounts

    suspend fun getHistoryCountsUntilId(id: Int): HistoryCounts

    suspend fun getLastHistoryItem(): HistoryItem?

    suspend fun insertHistoryItem(historyItem: HistoryItem)

    suspend fun isHistoryNotEmpty(): Boolean

    suspend fun deleteHistoryItemById(id: Int)

    suspend fun deleteAllHistoryItems()
}