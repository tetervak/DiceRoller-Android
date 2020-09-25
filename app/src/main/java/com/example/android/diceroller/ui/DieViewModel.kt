package com.example.android.diceroller.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.diceroller.model.Die

class DieViewModel : ViewModel() {

    private val die: Die = Die()

    private val dieMutableLiveData: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }

    init{
       dieMutableLiveData.value = die.value
    }

    val dieValue: LiveData<Int>
    get() = dieMutableLiveData

    fun roll(){
        die.roll()
        dieMutableLiveData.value = die.value
    }
}