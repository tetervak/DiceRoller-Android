
package com.example.android.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.android.diceroller.databinding.ActivityMainBinding
import com.example.android.diceroller.model.Die
import com.example.android.diceroller.ui.DieViewModel

class MainActivity : AppCompatActivity() {

    companion object{
        const val CURRENT_DIE_VALUE = "current_die_value"
    }

    private val model: DieViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rollButton.setOnClickListener { rollDice() }

        // get the model, set the observer for the die value
        model.dieValue.observe(this){ displayDice(it)}

    }

    private fun rollDice() {
        Toast.makeText(this, getString(R.string.dice_rolled), Toast.LENGTH_SHORT).show()
        model.roll()
    }

    private fun displayDice(value: Int) {
        binding.resultText.text = if (value > 0) value.toString() else " "
    }
}
