package com.example.eat_reat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var db = FirebaseFirestore.getInstance().collection("UserDetails")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        fabProfile.setOnClickListener{
            val intent = Intent(applicationContext, profileActivity::class.java)
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

    }




}