package ca.tetervak.diceroller.domain

class RollData(
    val values: List<Int>
){
    val numberOfDice: Int = values.size
    val total: Int = values.sum()

    override fun toString(): String {
        return "RollData(values=$values, numberOfDice=$numberOfDice, total=$total)"
    }
}