package ca.tetervak.diceroller.ui.roller

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.tetervak.diceroller.domain.GetNumberOfDiceUseCase
import ca.tetervak.diceroller.domain.GetRollDataUseCase
import ca.tetervak.diceroller.domain.GetRollerSavedStateUseCase
import ca.tetervak.diceroller.domain.HistoryCounts
import ca.tetervak.diceroller.domain.HistoryItem
import ca.tetervak.diceroller.domain.ResetHistoryUseCase
import ca.tetervak.diceroller.domain.RollData
import ca.tetervak.diceroller.domain.RollerSavedState
import ca.tetervak.diceroller.domain.SaveRollToHistoryUseCase
import ca.tetervak.diceroller.domain.toUpdatedBy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class RollerViewModel @Inject constructor(
    private val getRollDataUseCase: GetRollDataUseCase,
    private val saveRollToHistoryUseCase: SaveRollToHistoryUseCase,
    private val resetHistoryUseCase: ResetHistoryUseCase,
    private val getRollerSavedStateUseCase: GetRollerSavedStateUseCase,
    getNumberOfDiceUseCase: GetNumberOfDiceUseCase
) : ViewModel() {

    private val _rollerUiState: MutableState<RollerUiState> = mutableStateOf(RollerUiState.Loading)
    val rollerUiState: State<RollerUiState> = _rollerUiState

    val numberOfDice: StateFlow<Int> = getNumberOfDiceUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 3
        )

    init {
        viewModelScope.launch {
            val rollerSavedState: RollerSavedState = getRollerSavedStateUseCase()
            if (rollerSavedState is RollerSavedState.Saved) {
                val historyItem: HistoryItem = rollerSavedState.historyItem
                val historyCounts: HistoryCounts = rollerSavedState.historyCounts
                _rollerUiState.value = RollerUiState.Rolled(
                    rollData = historyItem.rollData,
                    historyCounts = historyCounts,
                    date = historyItem.date,
                )
            } else {
                _rollerUiState.value = RollerUiState.NotRolled
            }
        }
    }

    fun onRoll() = viewModelScope.launch {
        val date = Date()
        val rollData: RollData = getRollDataUseCase(numberOfDice.value)
        saveRollToHistoryUseCase(rollData = rollData, date = date)
        val newCounts: HistoryCounts = when (val uiState: RollerUiState = rollerUiState.value) {
            is RollerUiState.Rolled -> uiState.historyCounts.toUpdatedBy(rollTotal = rollData.total)
            else -> HistoryCounts(
                historyLength = 1, historyTotal = rollData.total
            )
        }
        _rollerUiState.value = RollerUiState.Rolled(
            rollData = rollData, historyCounts = newCounts, date = date
        )
    }

    fun onReset() = viewModelScope.launch {
        resetHistoryUseCase()
        _rollerUiState.value = RollerUiState.NotRolled
    }
}

