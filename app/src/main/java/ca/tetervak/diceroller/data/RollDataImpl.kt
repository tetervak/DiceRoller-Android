package ca.tetervak.diceroller.data

import ca.tetervak.diceroller.domain.RollData

class RollDataImpl(
    override val values: List<Int>
) : RollData {
    override val numberOfDice: Int = values.size
    override val total: Int = values.sum()

    override fun toString(): String {
        return "RollData(values=$values, numberOfDice=$numberOfDice, total=$total)"
    }
}