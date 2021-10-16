package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    lateinit var zero: Button
    lateinit var one: Button
    lateinit var two: Button
    lateinit var three: Button
    lateinit var four: Button
    lateinit var five: Button
    lateinit var six: Button
    lateinit var seven: Button
    lateinit var eight: Button
    lateinit var nine: Button
    lateinit var point: Button

    lateinit var addition: Button
    lateinit var subtraction: Button
    lateinit var multiplication: Button
    lateinit var division: Button
    lateinit var modulus: Button
    lateinit var equal: Button
    lateinit var clear_all: Button
    lateinit var back: Button
    lateinit var window: Button
    var rotation: Button? = null

    lateinit var input_field: TextView
    var history: TextView? = null
    var history_landscape: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get input fields
        input_field = findViewById(R.id.input_field)
        history = findViewById(R.id.history)
        history?.append("History: ")
        history_landscape = findViewById(R.id.history_landscape)
        history_landscape?.append("History: ")

        getUIElements()
        setListeners()

        if (savedInstanceState != null) {
            var currentValue = savedInstanceState.getCharSequence("currentValue")
            input_field.setText(currentValue)
            history?.append(currentValue)
            history_landscape?.append(currentValue)
        }

    }

    private fun getUIElements() {
        // Get all operands
        zero = findViewById(R.id.zero)
        one = findViewById(R.id.one)
        two = findViewById(R.id.two)
        three = findViewById(R.id.three)
        four = findViewById(R.id.four)
        five = findViewById(R.id.five)
        six = findViewById(R.id.six)
        seven = findViewById(R.id.seven)
        eight = findViewById(R.id.eight)
        nine = findViewById(R.id.nine)
        point = findViewById(R.id.point)

        // Get all operators
        addition = findViewById(R.id.addition_btn)
        subtraction = findViewById(R.id.subtraction_btn)
        multiplication = findViewById(R.id.multiplication_btn)
        division = findViewById(R.id.division_btn)
        modulus = findViewById(R.id.modulus_btn)
        equal = findViewById(R.id.equal_btn)
        back = findViewById(R.id.back_btn)
        clear_all = findViewById(R.id.clear_btn)
        window = findViewById(R.id.window)
        rotation = findViewById(R.id.rotation)

    }

    private fun setListeners() {
        // Set listeners on operands
        zero.setOnClickListener{
            input_field.append("0")
        }
        one.setOnClickListener{
            input_field.append("1")
        }
        two.setOnClickListener{
            input_field.append("2")
        }
        three.setOnClickListener{
            input_field.append("3")
        }
        four.setOnClickListener{
            input_field.append("4")
        }
        five.setOnClickListener{
            input_field.append("5")
        }
        six.setOnClickListener{
            input_field.append("6")
        }
        seven.setOnClickListener{
            input_field.append("7")
        }
        eight.setOnClickListener{
            input_field.append("8")
        }
        nine.setOnClickListener{
            input_field.append("9")
        }
        point.setOnClickListener{
            input_field.append(".")
        }

        // Set listeners on operators
        addition.setOnClickListener{
            input_field.append("+")
        }
        subtraction.setOnClickListener{
            input_field.append("-")
        }
        multiplication.setOnClickListener{
            input_field.append("x")
        }
        division.setOnClickListener{
            input_field.append("/")
        }
        modulus.setOnClickListener{
            input_field.append("%")
        }

        // Set listeners on config buttons
        clear_all.setOnClickListener{
            if (input_field.text.toString().length != 0) {
                history?.append(" " + input_field.text)
                history_landscape?.append(" " + input_field.text)
            }
            input_field.text = ""

        }
        window.setOnClickListener{
            Toast.makeText(this, "Portrait Layout", Toast.LENGTH_LONG).show()
        }
        rotation?.setOnClickListener{
            Toast.makeText(this, "Landscape Layout", Toast.LENGTH_LONG).show()
        }
        back.setOnClickListener{
            var str: String = input_field.getText().toString()
            if (str.length > 1) {
                str = str.substring(0, str.length - 1)
                input_field.setText(str)
            } else if (str.length <= 1) {
                input_field.setText("")
            }
        }
        equal.setOnClickListener{
            result()
        }
    }

    private  fun result() {
        var str = input_field.getText().toString()
        val operators = listOf("+", "-", "x", "/", "%")
        for (value in operators) {
            if(str.contains(value)) {
                when(value) {
                    "+" -> {
                        val new_str = str.split("+")
                        val value = new_str[0].toFloat() + new_str[1].toFloat()
                        input_field.setText(value.toString())
                    }
                    "-" -> {
                        val new_str = str.split("-")
                        val value = new_str[0].toFloat() - new_str[1].toFloat()
                        input_field.setText(value.toString())
                    }
                    "x" -> {
                        val new_str = str.split("x")
                        val value = new_str[0].toFloat() * new_str[1].toFloat()
                        input_field.setText(value.toString())
                    }
                    "/" -> {
                        val new_str = str.split("/")
                        val value = new_str[0].toFloat() / new_str[1].toFloat()
                        input_field.setText(value.toString())
                    }
                    "%" -> {
                        val new_str = str.split("%")
                        val value = new_str[0].toFloat() % new_str[1].toFloat()
                        input_field.setText(value.toString())
                    }

                    else -> { return  }
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putCharSequence("currentValue",input_field.text)
    }
}