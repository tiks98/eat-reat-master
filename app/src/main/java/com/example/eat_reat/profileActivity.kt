package com.example.eat_reat

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_profile.*


class profileActivity : AppCompatActivity() {

    var db = FirebaseFirestore.getInstance().collection("UserDetails")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)







        if (Firebase.auth.currentUser?.uid == db.document().id) {
//            firstNameTxtView.text = "First Name: ${UserDetails().firstName}"
//            lastNameTxtView.text = "Last Name:  ${UserDetails().lastName}"
//            addressLineOneTxtView.text = "Address Line 1: ${UserDetails().addressLineOne}"
//            addressLineTwoTxtView.text = "Address Line 2: ${UserDetails().addressLineTwo}"
//            cityTxtView.text = "City: ${UserDetails().city}"
//            provinceTxtView.text = "Province: ${UserDetails().province}"
//            postalCodeTxtView.text = "Postal Code: ${UserDetails().postalCode}"
//            emailTxtView.text = "Email: ${Firebase.auth.currentUser?.email}"
        }




        firstNameTxtView.text = "Name: ${Firebase.auth.currentUser?.displayName}"
        addressLineOneTxtView.text = "Address Line 1: ${UserDetails()}"
        addressLineTwoTxtView.text = "Address Line 2: ${UserDetails().addressLineTwo}"
        cityTxtView.text = "City: ${UserDetails().city}"
        provinceTxtView.text = "Province: ${UserDetails().province}"
        postalCodeTxtView.text = "Postal Code: ${UserDetails().postalCode}"
        emailTxtView.text = "Email: ${Firebase.auth.currentUser?.email}"


        addNewAddressBtn.setOnClickListener {
            val intent = Intent(applicationContext, AskAddressActivity::class.java)
            startActivity(intent)
        }
    }
}
