package ca.tetervak.diceroller.domain

class RollData(
    val values: List<Int>,
    val total: Int
){
    constructor(values: List<Int>): this(values, values.sum())

    val numberOfDice: Int = values.size

    override fun toString(): String {
        return "RollData(values=$values, numberOfDice=$numberOfDice, total=$total)"
    }
}