package ca.tetervak.diceroller.data

import ca.tetervak.diceroller.domain.RollData

interface RollerService {
    fun getRollData(numberOfDice: Int): RollData
}