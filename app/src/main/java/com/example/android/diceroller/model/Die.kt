package com.example.android.diceroller.model

import java.lang.IllegalArgumentException

class Die(dieValue: Int = 0) {

    // zero means not rolled yet
    var value: Int = dieValue
    set(n){
        if(n in 0..6){
            field = n
        }else{
            throw IllegalArgumentException("Illegal dice value $n")
        }
    }

    fun roll(){
        value = (1..6).random()
    }
}