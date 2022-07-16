package com.esteban.lopez.estebancalculator

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.esteban.lopez.estebancalculator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var actualNumber: Double = 0.0
    var operation: String = ""
    var finalized = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createOnClicks()
    }

    private fun createOnClicks() {
        binding.textView.showSoftInputOnFocus = false
        binding.textView.isCursorVisible = false

        binding.cero.setOnClickListener{
            pressNumber((it as Button).text.toString())
        }
        binding.one.setOnClickListener{
            pressNumber((it as Button).text.toString())
        }
        binding.two.setOnClickListener{
            pressNumber((it as Button).text.toString())
        }
        binding.three.setOnClickListener{
            pressNumber((it as Button).text.toString())
        }
        binding.four.setOnClickListener{
            pressNumber((it as Button).text.toString())
        }
        binding.five.setOnClickListener{
            pressNumber((it as Button).text.toString())
        }
        binding.six.setOnClickListener{
            pressNumber((it as Button).text.toString())
        }
        binding.seven.setOnClickListener{
            pressNumber((it as Button).text.toString())
        }
        binding.eight.setOnClickListener{
            pressNumber((it as Button).text.toString())
        }
        binding.nine.setOnClickListener{
            pressNumber((it as Button).text.toString())
        }

        binding.coma.setOnClickListener {
            if (!binding.textView.text.contains(".")) {
                binding.textView.setText(binding.textView.text.toString() + ".")
            }
        }

        binding.plus.setOnClickListener {
            onOperation()
            operation = "plus"
        }

        binding.minus.setOnClickListener {
            onOperation()
            operation = "subtract"
        }

        binding.multiply.setOnClickListener {
            onOperation()
            operation = "multiply"
        }

        binding.divide.setOnClickListener {
            onOperation()
            operation = "divide"
        }

        binding.equal.setOnClickListener {
            val number = binding.textView.text.toString()
            val result = doOperation(
                operation,
                if (number.isNumeric()) number.toDouble() else 0.0
            ).toString()
            actualNumber = result.toDouble()
            binding.textView.setText(result)
            operation = ""
            finalized = true
        }
    }

    private fun doOperation(operation: String, number: Double): Number {

        val result = when (operation) {
            "plus" -> {
                actualNumber + number
            }
            "subtract" -> {
                actualNumber - number
            }
            "divide" -> {
                actualNumber / number
            }
            "multiply" -> {
                actualNumber * number
            }
            else -> {
                actualNumber
            }
        }
        return if (result.rem(1).equals(0.0)) {
            result.toInt()
        } else {
            result
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true;
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.clean -> {
                binding.textView.setText("")
                actualNumber = 0.0
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    fun onOperation() {
        val number = binding.textView.text.toString()
        actualNumber = if (number.isNumeric()) number.toDouble() else 0.0
        binding.textView.setText("")
    }

    fun String.isNumeric(): Boolean {
        return this.toDoubleOrNull() != null
    }

    fun pressNumber(number:String){
        val actual = binding.textView.text.toString()
        binding.textView.setText(if (!finalized) actual+number else "" + number)
        finalized = false
    }
}