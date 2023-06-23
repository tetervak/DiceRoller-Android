package ca.tetervak.diceroller.domain

import ca.tetervak.diceroller.data.repository.HistoryItemRepository
import java.util.Date
import javax.inject.Inject

class SaveRollToHistoryUseCase @Inject constructor(
    private val repository: HistoryItemRepository
) {
    suspend operator fun invoke(rollData: RollData, date: Date) {
        val historyItem = HistoryItem(
            rollData = rollData, date = date
        )
        repository.insertHistoryItem(historyItem)
    }
}