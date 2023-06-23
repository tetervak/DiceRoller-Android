package ca.tetervak.diceroller.domain

import ca.tetervak.diceroller.data.repository.HistoryItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetHistoryDataUseCase @Inject constructor(
    private val repository: HistoryItemRepository
) {
    operator fun invoke(): Flow<HistoryData> = repository.getAllHistoryItemsFlow().map { list ->
            HistoryData(historyItems = list, historyTotal = list.sumOf { item -> item.rollTotal })
        }
}