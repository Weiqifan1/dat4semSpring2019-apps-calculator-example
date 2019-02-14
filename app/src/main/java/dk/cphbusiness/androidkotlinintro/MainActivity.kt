package dk.cphbusiness.androidkotlinintro

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TableRow

import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tableLayout {
            isShrinkAllColumns = true
            var buttons = mutableMapOf<String, Button>()
            fun createRow(vararg buttonNames: String) {
                tableRow {
                    for (name in buttonNames) buttons[name] = (button(name))
                    }
                }
            tableRow {
                button("CLR") {
                    onClick {
                        alert("Hi, I'm Roy", "Have you tried turning it off and on again?") {
                            yesButton { toast("Oh…") }
                            noButton {}
                            }.show()
                        }
                    }
                button("7") {
                    onClick {
                        val countries = listOf("Russia", "USA", "Japan", "Australia")
                        selector("Where are you from?", countries) { _, i ->
                            toast("So you're living in ${countries[i]}, right?")
                            }
                        }
                    }
                button("8")
                button("9")
                button("/")
                }
            tableRow {
                button("STO")
                button("4")
                button("5")
                button("6")
                button("*")
                }
            createRow("RCL", "1", "2", "3", "+")
            }.applyRecursively {
            when (it) {
                is TableRow -> it.applyRecursively {
                    when (it) {
                        is Button -> {
                            //it.backgroundColor = 0xffff00.opaque
                            it.background = ColorDrawable(Color.parseColor("#F8F2F2"))
                            it.maxWidth = dip(40)
                            }
                        }
                    }
                }
            }

        }
    }


