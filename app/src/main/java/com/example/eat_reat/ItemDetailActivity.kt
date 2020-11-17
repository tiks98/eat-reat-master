package com.example.eat_reat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.activity_main.*

class ItemDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        var itemName = intent.getStringExtra("itemName")
        itemNameTxtView.text = itemName

        var itemDesc = intent.getStringExtra("itemDesc")
        itemDescTxtView.text = itemDesc

        var itemImage = intent.extras?.getInt("itemImage")
        if (itemImage != null) {
            itemImageView.setImageResource(itemImage)
        }

        addCartBtn.setOnClickListener{
            val intent = Intent(applicationContext, CartActivity::class.java)
            intent.putExtra("itemName", itemName)
            intent.putExtra("itemImage", itemImage)
            startActivity(intent)
        }
    }
}