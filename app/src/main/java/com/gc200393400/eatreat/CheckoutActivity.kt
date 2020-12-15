package com.gc200393400.eatreat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_checkout.*

//reference: https://github.com/ifotn/comp3025-restorater-f20

class CheckoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        var Total = intent.getStringExtra("Total")
        checkoutTotal.text = "Your Total: $ ${Total}"

        var addressLineOne = intent.getStringExtra("addressLineOne")
        addressLineOneTxtView.text = "Address Line 1: ${addressLineOne}"

        var addressLineTwo = intent.getStringExtra("addressLineTwo")
        addressLineTwoTxtView.text = "Address Line 2: ${addressLineTwo}"

        var city = intent.getStringExtra("city")
        cityTxtView.text = "City: ${city}"

        var province = intent.getStringExtra("province")
        provinceTxtView.text = "Province: ${province}"

        var postalCode = intent.getStringExtra("postalCode")
        postalCodeTxtView.text = "Postal Code: ${postalCode}"

        homeBtn.setOnClickListener{
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
}