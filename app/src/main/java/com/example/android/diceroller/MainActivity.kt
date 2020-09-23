
package com.example.android.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.android.diceroller.databinding.ActivityMainBinding
import com.example.android.diceroller.model.Die

class MainActivity : AppCompatActivity() {

    companion object{
        const val CURRENT_DIE_VALUE = "current_die_value"
    }

    private lateinit var binding: ActivityMainBinding

    private val die = Die()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rollButton.setOnClickListener { rollDice() }

        if(savedInstanceState is Bundle){
            die.value = savedInstanceState.getInt(CURRENT_DIE_VALUE)
        }
        displayDice()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(CURRENT_DIE_VALUE, die.value)
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
