package ca.tetervak.diceroller.domain

import ca.tetervak.diceroller.data.repository.HistoryItemRepository
import javax.inject.Inject

class GetRollerSavedStateUseCase @Inject constructor(
    val repository: HistoryItemRepository
) {
    suspend operator fun invoke(): RollerSavedState =
        if(repository.isHistoryNotEmpty()){
            val historyCounts: HistoryCounts = repository.getHistoryCounts()
            val lastHistoryItem: HistoryItem = repository.getLastHistoryItem()!!
            RollerSavedState.Saved(
                historyCounts = historyCounts,
                historyItem = lastHistoryItem
            )
        } else {
            RollerSavedState.NotSaved
        }
}