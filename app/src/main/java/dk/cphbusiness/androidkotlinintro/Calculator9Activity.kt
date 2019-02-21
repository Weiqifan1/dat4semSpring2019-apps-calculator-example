package dk.cphbusiness.androidkotlinintro

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_calculator9.*

class Calculator9Activity : AppCompatActivity() {
    val LOG_TAG = Calculator9Activity::class.java.simpleName
    lateinit var calculator: Calculator
    var inputValue = 0L
    var inputActive = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) calculator = Calculator()
        else calculator =
            savedInstanceState.getSerializable("calculator") as Calculator? ?: Calculator()

        setContentView(R.layout.activity_calculator9)
        // val eb = findViewById<Button>(R.id.enterButton)
/*
        enterButton.setOnClickListener {
            val number = editText2.text.toString().toLongOrNull()

            if (number == null) {
                val top = calculator.stackX
                calculator.push(top)
                }
            else calculator.enter(number)
            updateStack()
            editText2.setText("")
            }
*/
        updateStack()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("calculator", calculator)
    }


    fun updateStack() {
        if (inputActive) {
            stack_t_view.setText(calculator.stackZ.text)
            stack_z_view.setText(calculator.stackY.text)
            stack_y_view.setText(calculator.stackX.text)
            stack_x_view.setText(inputValue.toString())
            }
        else {
            stack_t_view.setText(calculator.stackT.text)
            stack_z_view.setText(calculator.stackZ.text)
            stack_y_view.setText(calculator.stackY.text)
            stack_x_view.setText(calculator.stackX.text)
            }
        }

    fun doDigit(view: View) {
        fun updateInput(digit: Int) { inputValue = 10*inputValue + digit}
        // checkInput()
        println("${view.id}")
        when (view) {
            number_0_button -> updateInput(0)
            number_1_button -> updateInput(1)
            number_2_button -> updateInput(2)
            number_3_button -> updateInput(3)
            number_4_button -> updateInput(4)
            number_5_button -> updateInput(5)
            number_6_button -> updateInput(6)
            number_7_button -> updateInput(7)
            number_8_button -> updateInput(8)
            number_9_button -> updateInput(9)
        }
        inputActive = true
        updateStack()
    }

    fun doEnter(view: View) {
        if (inputActive) calculator = calculator.enter(inputValue)
        else {
            val top = calculator.stackX
            calculator = calculator.push(top)
        }
        inputValue = 0L
        inputActive = false
        updateStack()
    }

    fun doOperation(view: View) {
        if (inputActive) calculator = calculator.enter(inputValue)
        when (view) {
            div_button -> calculator = calculator.div()
            times_button -> calculator = calculator.times()
            minus_button -> calculator = calculator.minus()
            plus_button -> calculator = calculator.plus()
            clear_x_button -> calculator = calculator.clear()
            store_button -> calculator = calculator.store()
            recall_button -> calculator = calculator.recall()
            swap_button -> calculator = calculator.swap()
            roll_down_button -> calculator = calculator.rollDown()
            undo_button -> {
                calculator = calculator.history ?: calculator

                // val h = calculator.history
                // if (h != null) calculator = h
                }
            option_button -> {
                Log.d(LOG_TAG, "Options clicked")
                intent = Intent(this, OptionsActivity::class.java)
                startActivity(intent)
                }
            }
        inputValue = 0L
        inputActive = false
        updateStack()
    }

}
