package com.example.android.diceroller.ui

import androidx.lifecycle.ViewModel
import com.example.android.diceroller.model.Die

class DieViewModel : ViewModel() {

    val die: Die = Die()
}