
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

    private lateinit var binding: ActivityMainBinding

    private lateinit var die: Die

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rollButton.setOnClickListener { rollDice() }

        // get the model, pick the die object from the model
        val viewModel: DieViewModel by viewModels()
        die = viewModel.die

        // show the number
        displayDice()
    }

    private fun rollDice() {
        Toast.makeText(this, getString(R.string.dice_rolled), Toast.LENGTH_SHORT).show()
        die.roll()
        displayDice()
    }

    private fun displayDice() {
        binding.resultText.text =
            if (die.value > 0)
                die.value.toString()
            else " "
    }
}
