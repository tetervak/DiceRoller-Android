package ca.tetervak.diceroller.domain

import ca.tetervak.diceroller.data.service.RollerService
import javax.inject.Inject

class GetRollDataUseCase @Inject constructor(
    private val service: RollerService
){
    operator fun invoke(numberOfDice: Int) =
        service.getRollData(numberOfDice = numberOfDice)
}