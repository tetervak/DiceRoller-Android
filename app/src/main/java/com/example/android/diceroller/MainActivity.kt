/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.android.diceroller.model.Die
import java.util.*

class MainActivity : AppCompatActivity() {

    private val die = Die()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.roll_button)
        rollButton.setOnClickListener { rollDice() }

        if(savedInstanceState is Bundle){
            die.value = savedInstanceState.getInt("current_die_value")
        }else{
            die.roll()
        }
        displayDice()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("current_die_value", die.value)
    }

    private fun rollDice() {
        Toast.makeText(this, getString(R.string.dice_rolled), Toast.LENGTH_SHORT).show()
        die.roll()
        displayDice()
    }

    private fun displayDice() {
        val randomInt = die.value

        val resultText: TextView = findViewById(R.id.result_text)
        resultText.text = randomInt.toString()
    }
}
