package ca.tetervak.diceroller.data.repository

import ca.tetervak.diceroller.data.local.history.HistoryItemDao
import ca.tetervak.diceroller.data.local.history.LocalHistoryCounts
import ca.tetervak.diceroller.data.local.history.LocalHistoryItem
import ca.tetervak.diceroller.domain.HistoryCounts
import ca.tetervak.diceroller.domain.HistoryItem
import ca.tetervak.diceroller.domain.RollData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalHistoryItemRepository @Inject constructor(
    private val historyItemDao: HistoryItemDao
) : HistoryItemRepository {
    override fun getAllHistoryItemsFlow(): Flow<List<HistoryItem>> =
        historyItemDao.getAllHistoryItemsFlow().map { list ->
            list.map { it.toHistoryItem() }
        }.flowOn(Dispatchers.IO)

    override suspend fun getHistoryItemById(id: Int): HistoryItem = withContext(Dispatchers.IO) {
        historyItemDao.getHistoryItemById(id).toHistoryItem()
    }

    override suspend fun getHistoryTotal(): Int = withContext(Dispatchers.IO) {
        historyItemDao.getHistoryTotal()
    }

    override suspend fun getHistoryCounts(): HistoryCounts = withContext(Dispatchers.IO) {
        historyItemDao.getHistoryCounts().toHistoryCounts()
    }

    override suspend fun getHistoryCountsUntilId(id: Int): HistoryCounts =
        withContext(Dispatchers.IO) {
            historyItemDao.getHistoryCountsUntilId(id).toHistoryCounts()
        }

    override suspend fun getLastHistoryItem(): HistoryItem? = withContext(Dispatchers.IO) {
        historyItemDao.getLastHistoryItem()?.toHistoryItem()
    }

    override suspend fun isHistoryNotEmpty(): Boolean = withContext(Dispatchers.IO) {
        historyItemDao.isHistoryNotEmpty()
    }

    override suspend fun insertHistoryItem(historyItem: HistoryItem) = withContext(Dispatchers.IO) {
        historyItemDao.insertHistoryItem(historyItem.toLocalHistoryItem())
    }

    override suspend fun deleteHistoryItemById(id: Int) = withContext(Dispatchers.IO) {
        historyItemDao.deleteHistoryItemById(id)
    }

    override suspend fun deleteAllHistoryItems() = withContext(Dispatchers.IO) {
        historyItemDao.deleteAllHistoryItems()
    }
}

fun HistoryItem.toLocalHistoryItem(): LocalHistoryItem = LocalHistoryItem(
    id = this.id,
    rollValues = this.rollData.values.joinToString(separator = "+"),
    rollTotal = this.rollData.total,
    date = this.date
)

fun LocalHistoryItem.toHistoryItem(): HistoryItem = HistoryItem(
    id = this.id, rollData = RollData(
        values = this.rollValues.split("+").map { it.toInt() },
        total = this.rollTotal,
    ), date = this.date
)

fun LocalHistoryCounts.toHistoryCounts(): HistoryCounts = HistoryCounts(
    historyLength = this.historyLength, historyTotal = this.historyTotal ?: 0
)