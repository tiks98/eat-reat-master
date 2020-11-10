package com.example.eat_reat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_ask_address.*

class AskAddressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask_address)

        //reading quantity value from cartActivity
        val quantity = intent.getStringExtra("quantity")
        enteredQuantityTxtView.text = "Quantity $quantity"
    }
}