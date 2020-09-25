package com.example.android.diceroller.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.diceroller.model.Die

class DieViewModel : ViewModel() {

    private val die: Die = Die()
    private val _dieValue: MutableLiveData<Int> = MutableLiveData<Int>()

    init{
       _dieValue.value = die.value
    }

    val dieValue: LiveData<Int>
    get() = _dieValue

    fun roll(){
        die.roll()
        _dieValue.value = die.value
    }
}