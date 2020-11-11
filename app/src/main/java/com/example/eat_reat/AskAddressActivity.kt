package com.example.eat_reat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_ask_address.*

class AskAddressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask_address)

        logoutFloatingActionBtn.setOnClickListener{
            //signs out user
            FirebaseAuth.getInstance().signOut()
            finish()

            val intent = Intent(applicationContext, SignInActivity::class.java)
            startActivity(intent)
        }

        submitBtn.setOnClickListener{
            if (addressLineOneEditTxt.text.toString().isNotEmpty() && cityEditTxt.text.toString().isNotEmpty() && provinceEditTxt.text.toString().isNotEmpty() && postalCodeEditTxt.text.toString().isNotEmpty()){
                //route after user submits details
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "Please Enter all details", Toast.LENGTH_LONG).show()
            }


        }
    }

    override fun onStart() {
        super.onStart()

        val user = Firebase.auth.currentUser
        if (user == null){

            val intent = Intent(applicationContext, SignInActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStop() {
        super.onStop()
    }
}