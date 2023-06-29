package ca.tetervak.diceroller.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.tetervak.diceroller.domain.GetNumberOfDiceUseCase
import ca.tetervak.diceroller.domain.SetNumberOfDiceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    getNumberOfDiceUseCase: GetNumberOfDiceUseCase,
    private val setGetNumberOfDiceUseCase: SetNumberOfDiceUseCase
):ViewModel() {

    val numberOfDice: StateFlow<Int> = getNumberOfDiceUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 3
        )

    fun updateNumberOfDice(numberOfDice: Int) =
        viewModelScope.launch {
            setGetNumberOfDiceUseCase(numberOfDice = numberOfDice)
        }
}