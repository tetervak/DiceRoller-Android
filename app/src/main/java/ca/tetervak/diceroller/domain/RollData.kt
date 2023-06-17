package ca.tetervak.diceroller.domain

interface RollData {
    val values: List<Int>
    val numberOfDice: Int
    val total: Int
}