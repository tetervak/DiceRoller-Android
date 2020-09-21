package com.example.android.diceroller.model

class Die(var value: Int = 1) {

    fun roll(){
        value = (1..6).random()
    }
}