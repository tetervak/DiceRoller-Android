package ca.tetervak.diceroller.ui.roller

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.tetervak.diceroller.domain.GetHistoryTotalUseCase
import ca.tetervak.diceroller.domain.GetRollDataUseCase
import ca.tetervak.diceroller.domain.ResetHistoryUseCase
import ca.tetervak.diceroller.domain.RollData
import ca.tetervak.diceroller.domain.SaveRollToHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class RollerViewModel @Inject constructor(
    private val getRollDataUseCase: GetRollDataUseCase,
    private val saveRollToHistoryUseCase: SaveRollToHistoryUseCase,
    private val getHistoryTotalUseCase: GetHistoryTotalUseCase,
    private val resetHistoryUseCase: ResetHistoryUseCase
) : ViewModel() {

    private val _rollerUiState: MutableState<RollerUiState> =
        mutableStateOf(RollerUiState.NotRolled)
    val rollerUiState: State<RollerUiState> = _rollerUiState

    fun onRoll() = viewModelScope.launch {
        val date = Date()
        val rollData: RollData = getRollDataUseCase(3)
        saveRollToHistoryUseCase(rollData = rollData, date = date)
        val historyTotal: Int = getHistoryTotalUseCase()
        _rollerUiState.value = RollerUiState.Rolled(
            rollData = rollData,
            historyTotal = historyTotal,
            date = date
        )
    }

    fun onReset() = viewModelScope.launch{
        resetHistoryUseCase()
        _rollerUiState.value = RollerUiState.NotRolled
    }
}

