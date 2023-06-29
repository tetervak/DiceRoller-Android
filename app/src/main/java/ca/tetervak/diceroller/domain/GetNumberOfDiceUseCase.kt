package ca.tetervak.diceroller.domain

import ca.tetervak.diceroller.data.repository.UserSettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNumberOfDiceUseCase @Inject constructor(
    private val repository: UserSettingsRepository
) {
    operator fun invoke(): Flow<Int> = repository.getNumberOfDiceFlow()
}