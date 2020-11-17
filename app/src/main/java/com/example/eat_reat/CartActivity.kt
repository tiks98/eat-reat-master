package com.example.eat_reat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cart.*


class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        var itemName = intent.getStringExtra("itemName")
        cartItemNameTxtView.text = itemName

        var itemImage = intent.extras?.getInt("itemImage")
        if (itemImage != null) {
            cartItemImageView.setImageResource(itemImage)
        }

        //creating a OnClickListner to go to another activity
        checkoutButton.setOnClickListener{
            //only invoke intent if the quantity is entered
            if (quantityEditText.text.toString().isNotEmpty()){

                val i = Intent(applicationContext, AskAddressActivity::class.java)


                i.putExtra("quantity", quantityEditText.text.toString())


                startActivity(i)
            }
            else {
                Toast.makeText(this, "Please enter quantity", Toast.LENGTH_LONG).show()
            }



        }

    }



}