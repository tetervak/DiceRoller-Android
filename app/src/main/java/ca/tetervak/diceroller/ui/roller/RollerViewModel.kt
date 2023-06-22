package ca.tetervak.diceroller.ui.roller

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ca.tetervak.diceroller.domain.GetRollDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RollerViewModel @Inject constructor(
    private val getRollDataUseCase: GetRollDataUseCase
) : ViewModel() {

    private val _rollerUiState: MutableState<RollerUiState> =
        mutableStateOf(RollerUiState.NotRolled)
    val rollerUiState: State<RollerUiState> = _rollerUiState

    fun onRoll() {
        _rollerUiState.value = RollerUiState.Rolled(
            rollData = getRollDataUseCase(3)
        )
    }

    fun onReset() {
        _rollerUiState.value = RollerUiState.NotRolled
    }
}

