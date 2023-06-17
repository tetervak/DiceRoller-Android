package ca.tetervak.diceroller.ui.roller

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ca.tetervak.diceroller.data.RollerService
import ca.tetervak.diceroller.data.RollerServiceImpl
import ca.tetervak.diceroller.domain.RollData

class RollerViewModel(
    private val service: RollerService = RollerServiceImpl()
) : ViewModel() {

    private val _rollerUiState: MutableState<RollerUiState> =
        mutableStateOf(RollerUiState.NotRolled)
    val rollerUiState: State<RollerUiState> = _rollerUiState

//    private val _messageUiState: MutableState<MessageUiState> = mutableStateOf(MessageUiState())
//    val messageUiState: State<MessageUiState> = _messageUiState

    fun onRoll(){
         _rollerUiState.value =
             RollerUiState.Rolled(
                 rollData = service.getRollData(3)
             )
//        _messageUiState.value =
//            _messageUiState.value.appendMessage("Rolled")
    }

    fun onReset(){
        _rollerUiState.value = RollerUiState.NotRolled
    }

//    fun onMessageDisplayed(message: String){
//        _messageUiState.value =
//            _messageUiState.value.withoutMessage(message)
//    }

}

sealed interface RollerUiState {
    data class Rolled(val rollData: RollData) : RollerUiState
    object NotRolled : RollerUiState
}
