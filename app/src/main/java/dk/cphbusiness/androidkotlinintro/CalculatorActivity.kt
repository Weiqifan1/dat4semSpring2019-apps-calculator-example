package dk.cphbusiness.androidkotlinintro

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.View

import kotlinx.android.synthetic.main.activity_calculator.*
import kotlinx.android.synthetic.main.content_calculator.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast

class CalculatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        button_enter.onClick { toast("Enter!") }
        }

    fun doDigit(view: View) {
        when (view) {
            is CalculatorKey -> toast("Du trykkede ${view.text}")
            else -> toast("Hovsa")
            }
        }

    }
