package ca.tetervak.diceroller.domain

import ca.tetervak.diceroller.data.repository.HistoryItemRepository
import javax.inject.Inject

class GetHistoryCountsUseCase @Inject constructor(
    private val repository: HistoryItemRepository
) {
    suspend operator fun invoke(): HistoryCounts =
        repository.getHistoryCounts()
}