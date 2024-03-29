package com.gc200393400.eatreat

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_item_detail.*

//reference: https://github.com/ifotn/comp3025-restorater-f20

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

            val FoodItems = FoodItems()
            FoodItems.userId = Firebase.auth.currentUser!!.uid.trim()
            FoodItems.foodItemName = itemName.toString().trim()
            FoodItems.foodItemPrice = itemPrice.toString().trim()
            FoodItems.foodImage = itemImage

            val user = FoodItems.userId!!.toString()
            var curUser = Firebase.auth.currentUser!!.uid
            val db = FirebaseFirestore.getInstance().collection("cartItems")
            val database = db.document(user).collection("cart")

            if (db.document(FoodItems.userId!!).toString() != Firebase.auth.currentUser!!.uid) {
                curUser = db.document().id
                FoodItems.foodItemId = database.document().id
                if (curUser === Firebase.auth.currentUser!!.uid) {
                    db.document(curUser).collection("cart").document(FoodItems.foodItemId!!).set(FoodItems)
                }
                else{
                    FoodItems.foodItemId = db.document().id
                    db.document(user).collection("cart").document(FoodItems.foodItemId!!).set(FoodItems)
                }
            }
            else {

            }
            Toast.makeText(this, "Your have added $itemName to cart", Toast.LENGTH_LONG).show()
        }
    }
}