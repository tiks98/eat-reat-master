package com.example.eat_reat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class OrderConfirmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirmation)

        var addressLineOne = intent.getStringExtra("addressLineOne")

        var addressLineTwo = intent.getStringExtra("addressLineTwo")

        var city = intent.getStringExtra("city")

        var province = intent.getStringExtra("province")

        var postalCode = intent.getStringExtra("postalCode")

    }
}