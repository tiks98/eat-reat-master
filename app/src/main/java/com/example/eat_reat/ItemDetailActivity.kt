package com.example.eat_reat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_item_detail.*

class ItemDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        var itemName = intent.getStringExtra("itemName")
        itemNameTxtView.text = itemName

        var itemPrice = intent.getStringExtra("itemPrice")
        itemPriceTxtView.text = itemPrice

        var itemDesc = intent.getStringExtra("itemDesc")
        itemDescTxtView.text = itemDesc

        var itemImage = intent.extras?.getInt("itemImage")
        if (itemImage != null) {
            itemImageView.setImageResource(itemImage)
        }

        addCartBtn.setOnClickListener{
            val intent = Intent(applicationContext, CartActivity::class.java)
//            intent.putExtra("itemName", itemName)
//            intent.putExtra("itemPrice", itemPrice)
            intent.putExtra("itemImage", itemImage)
//            startActivity(intent)

            val FoodItems = FoodItems()
            FoodItems.userId = Firebase.auth.currentUser!!.uid.trim()
            FoodItems.foodItemName = itemName.toString().trim()
            FoodItems.foodItemPrice = itemPrice.toString().trim()
            FoodItems.foodImage = itemImage

            val db = FirebaseFirestore.getInstance().collection("cartItems")
            FoodItems.foodItemId = db.document().id
            db.document(FoodItems.foodItemId!!).set(FoodItems)

            Toast.makeText(this, "Your have added $itemName to cart", Toast.LENGTH_LONG).show()
        }
    }
}