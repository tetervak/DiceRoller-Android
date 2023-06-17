package ca.tetervak.diceroller.ui.common

data class MessageUiState(val messages: List<String> = emptyList())

fun MessageUiState.appendMessage(message: String) =
    this.copy(
        messages = this.messages + message
    )

fun MessageUiState.withoutMessage(message: String) =
    this.copy(
        messages = this.messages.filterNot { it == message }
    )