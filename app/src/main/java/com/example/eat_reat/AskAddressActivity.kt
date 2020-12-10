package com.example.eat_reat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_ask_address.*

class AskAddressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask_address)



        submitBtn.setOnClickListener{
            if (addressLineOneEditTxt.text.toString().isNotEmpty() && cityEditTxt.text.toString().isNotEmpty() && provinceEditTxt.text.toString().isNotEmpty() && postalCodeEditTxt.text.toString().isNotEmpty()){
                //taking input from user from askaddress activity
                val userDetails = UserDetails()
                userDetails.addressLineOne = addressLineOneEditTxt.text.toString().trim()
                userDetails.addressLineTwo = addressLineTwoEditTxt.text.toString().trim()
                userDetails.city = cityEditTxt.text.toString().trim()
                userDetails.province = provinceEditTxt.text.toString().trim()
                userDetails.postalCode = postalCodeEditTxt.text.toString().trim()
                userDetails.userId = Firebase.auth.currentUser!!.uid.trim()



                //connect to firebase database
                val db = FirebaseFirestore.getInstance().collection("UserDetails")
                userDetails.addressId = db.document().id
                db.document(userDetails.addressId!!).set(userDetails)

                Toast.makeText(this, "Your Details have been saved", Toast.LENGTH_LONG).show()



                //route after user submits details
                val intent = Intent(applicationContext, ProfileActivity::class.java)
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