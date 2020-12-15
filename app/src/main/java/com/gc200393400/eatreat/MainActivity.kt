package com.gc200393400.eatreat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

//reference: https://github.com/ifotn/comp3025-restorater-f20

class MainActivity : AppCompatActivity() {

    var db = FirebaseFirestore.getInstance().collection("UserDetails")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        fabProfile.setOnClickListener{
            val intent = Intent(applicationContext, ProfileActivity::class.java)
            startActivity(intent)
        }

        cartFab.setOnClickListener{
            val intent = Intent(applicationContext, CartActivity::class.java)
            startActivity(intent)
        }

        logOutFAB.setOnClickListener{
            //signs out user
            FirebaseAuth.getInstance().signOut()
            finish()

            val intent = Intent(applicationContext, SignInActivity::class.java)
            startActivity(intent)
        }

        homeFab.setOnClickListener{
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

        VLL1.setOnClickListener{
            val intent = Intent(applicationContext, ItemDetailActivity::class.java)
            intent.putExtra("itemName", burgerNameTxtView.text.toString())
            intent.putExtra("itemPrice", burgerPriceTxtView.text.toString())
            intent.putExtra("itemDesc", burgerDescTxtView.text.toString())
            intent.putExtra("itemImage", R.drawable.burger)
            startActivity(intent)
        }

        VLL2.setOnClickListener{
            val intent = Intent(applicationContext, ItemDetailActivity::class.java)
            intent.putExtra("itemName", noodlesNameTxtView.text.toString())
            intent.putExtra("itemPrice", noodlesPriceTxtView.text.toString())
            intent.putExtra("itemDesc", noodlesDescTxtView.text.toString())
            intent.putExtra("itemImage", R.drawable.noodles)
            startActivity(intent)
        }

        VLL3.setOnClickListener{
            val intent = Intent(applicationContext, ItemDetailActivity::class.java)
            intent.putExtra("itemName", dalSoupNameTxtView.text.toString())
            intent.putExtra("itemPrice", dalSoupPriceTxtView.text.toString())
            intent.putExtra("itemDesc", dalSoupDescTxtView.text.toString())
            intent.putExtra("itemImage", R.drawable.soup)
            startActivity(intent)
        }

        VLL4.setOnClickListener{
            val intent = Intent(applicationContext, ItemDetailActivity::class.java)
            intent.putExtra("itemName", samosaNameTxtView.text.toString())
            intent.putExtra("itemPrice", samosaPriceTxtView.text.toString())
            intent.putExtra("itemDesc", samosaDescTxtView.text.toString())
            intent.putExtra("itemImage", R.drawable.samosa)
            startActivity(intent)
        }

        VLL5.setOnClickListener{
            val intent = Intent(applicationContext, ItemDetailActivity::class.java)
            intent.putExtra("itemName", burritoNameTxtView.text.toString())
            intent.putExtra("itemPrice", burritoPriceTxtView.text.toString())
            intent.putExtra("itemDesc", burritoDescTxtView.text.toString())
            intent.putExtra("itemImage", R.drawable.burrito)
            startActivity(intent)
        }

        VLL6.setOnClickListener{
            val intent = Intent(applicationContext, ItemDetailActivity::class.java)
            intent.putExtra("itemName", tacosNameTxtView.text.toString())
            intent.putExtra("itemPrice", tacosPriceTxtView.text.toString())
            intent.putExtra("itemDesc", tacosDescTxtView.text.toString())
            intent.putExtra("itemImage", R.drawable.tacos)
            startActivity(intent)
        }

        VLL7.setOnClickListener{
            val intent = Intent(applicationContext, ItemDetailActivity::class.java)
            intent.putExtra("itemName", butterChickenTxtView.text.toString())
            intent.putExtra("itemPrice", butterChickenPriceTxtView.text.toString())
            intent.putExtra("itemDesc", butterChickenDescTxtView.text.toString())
            intent.putExtra("itemImage", R.drawable.butterchicken)
            startActivity(intent)
        }

        VLL8.setOnClickListener{
            val intent = Intent(applicationContext, ItemDetailActivity::class.java)
            intent.putExtra("itemName", naanNameTxtView.text.toString())
            intent.putExtra("itemPrice", naanPriceTxtView.text.toString())
            intent.putExtra("itemDesc", naanDescTxtView.text.toString())
            intent.putExtra("itemImage", R.drawable.naan)
            startActivity(intent)
        }

    }




}