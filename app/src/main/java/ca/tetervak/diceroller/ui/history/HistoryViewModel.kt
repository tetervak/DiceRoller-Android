package ca.tetervak.diceroller.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.tetervak.diceroller.domain.GetHistoryDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    getHistoryDataUseCase: GetHistoryDataUseCase
): ViewModel() {

    val historyUiState: StateFlow<HistoryUiState> =
        getHistoryDataUseCase().map { historyData ->
            if(historyData.historyCounts.historyLength > 0){
                HistoryUiState.Loaded(
                    itemStates = historyData.historyItems.mapIndexed { index, historyItem ->
                        HistoryItemUiState(
                            count = index + 1,
                            historyItem = historyItem
                        )
                    },
                    historyCounts = historyData.historyCounts
                )
            } else {
                HistoryUiState.Empty
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HistoryUiState.Loading
        )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}