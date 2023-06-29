package ca.tetervak.diceroller.domain

import ca.tetervak.diceroller.data.repository.UserSettingsRepository
import javax.inject.Inject

class SetNumberOfDiceUseCase @Inject constructor(
    private val repository: UserSettingsRepository
) {
    suspend operator fun invoke(numberOfDice: Int) =
        repository.saveNumberOfDice(numberOfDice)
}