package dk.cphbusiness.androidkotlinintro

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window

class Calculator9Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_calculator9)
    }
}
