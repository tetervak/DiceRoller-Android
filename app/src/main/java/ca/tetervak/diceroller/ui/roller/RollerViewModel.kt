package ca.tetervak.diceroller.ui.roller

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ca.tetervak.diceroller.data.service.RollerService
import ca.tetervak.diceroller.domain.RollData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RollerViewModel @Inject constructor(
    private val service: RollerService
) : ViewModel() {

    private val _rollerUiState: MutableState<RollerUiState> =
        mutableStateOf(RollerUiState.NotRolled)
    val rollerUiState: State<RollerUiState> = _rollerUiState

    fun onRoll() {
        _rollerUiState.value = RollerUiState.Rolled(
            rollData = service.getRollData(3)
        )
    }

    fun onReset() {
        _rollerUiState.value = RollerUiState.NotRolled
    }
}

sealed interface RollerUiState {
    data class Rolled(val rollData: RollData) : RollerUiState
    object NotRolled : RollerUiState
}
