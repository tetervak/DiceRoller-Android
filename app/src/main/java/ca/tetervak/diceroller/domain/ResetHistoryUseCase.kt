package ca.tetervak.diceroller.domain

import ca.tetervak.diceroller.data.repository.HistoryItemRepository
import javax.inject.Inject

class ResetHistoryUseCase @Inject constructor(
    private val repository: HistoryItemRepository
) {
    suspend operator fun invoke() =
        repository.deleteAllHistoryItems()
}