package com.example.eat_reat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
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
                userDetails.firstName = firstNameEditTxt.text.toString().trim()
                userDetails.lastName = lastNameEditTxt.text.toString().trim()
                userDetails.addressLineOne = addressLineOneEditTxt.text.toString().trim()
                userDetails.addressLineTwo = addressLineTwoEditTxt.text.toString().trim()
                userDetails.city = cityEditTxt.text.toString().trim()
                userDetails.province = provinceEditTxt.text.toString().trim()
                userDetails.postalCode = postalCodeEditTxt.text.toString().trim()
                userDetails.email = emailAddressEditTxt.text.toString().trim()


                //connect to firebase database
                val db = FirebaseFirestore.getInstance().collection("UserDetails")
                userDetails.id = Firebase.auth.currentUser?.uid
                db.document(userDetails.id!!).set(userDetails)

                Toast.makeText(this, "Your Details have been saved", Toast.LENGTH_LONG).show()



                //route after user submits details
                val intent = Intent(applicationContext, profileActivity::class.java)
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