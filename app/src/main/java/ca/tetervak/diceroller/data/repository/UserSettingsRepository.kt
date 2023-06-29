package ca.tetervak.diceroller.data.repository

import kotlinx.coroutines.flow.Flow

interface UserSettingsRepository {

    fun getNumberOfDiceFlow(): Flow<Int>

    suspend fun saveNumberOfDice(numberOfDice: Int)
}