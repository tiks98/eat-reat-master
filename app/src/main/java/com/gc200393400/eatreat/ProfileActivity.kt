package com.gc200393400.eatreat

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.address_card.view.*

//reference: https://github.com/ifotn/comp3025-restorater-f20

class ProfileActivity : AppCompatActivity() {

    var db = FirebaseFirestore.getInstance()
    private var adapter: AddressAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        //setting recyclerview
        addressCardRecyclerView.layoutManager = LinearLayoutManager(this)

        //query DB for all address entered by the user
        val query =
            db.collection("UserDetails").whereEqualTo("userId", Firebase.auth.currentUser?.uid)


        val options =
            FirestoreRecyclerOptions.Builder<UserDetails>().setQuery(query, UserDetails::class.java)
                .build()
        adapter = AddressAdapter(options)
        addressCardRecyclerView.adapter = adapter

        addNewAddressBtn.setOnClickListener {
            val intent = Intent(applicationContext, AskAddressActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onStart() {
        super.onStart()
        adapter!!.startListening()
    }

    override fun onStop() {
        super.onStop()
        if (adapter != null) {
            adapter!!.stopListening()
        }
    }

    //bind the data to recyclerview
    private inner class AddressViewHolder internal constructor(private val view: View) :
        RecyclerView.ViewHolder(view) {}

    private inner class AddressAdapter internal constructor(options: FirestoreRecyclerOptions<UserDetails>) :
        FirestoreRecyclerAdapter<UserDetails, AddressViewHolder>(options) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
            //inflate layout file to populate the recyclerview
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.address_card, parent, false)
            return AddressViewHolder(view)
        }

        override fun onBindViewHolder(
            holder: AddressViewHolder,
            position: Int,
            model: UserDetails
        ) {
            //populate all the data from firebase into matching textview
            holder.itemView.firstNameTxtView.text =
                "Name: " + Firebase.auth.currentUser!!.displayName
            holder.itemView.addressLineOneTxtView.text = "Address Line 1: " + model.addressLineOne
            holder.itemView.addressLineTwoTxtView.text = "Address Line 2: " + model.addressLineTwo
            holder.itemView.cityTxtView.text = "City: " + model.city
            holder.itemView.provinceTxtView.text = "Province: " + model.province
            holder.itemView.postalCodeTxtView.text = "Postal Code: " + model.postalCode

            var Total = intent.getStringExtra("Total")

            holder.itemView.defaultAddressBtn.setOnClickListener {
                val intent = Intent(applicationContext, CheckoutActivity::class.java)
                intent.putExtra("Total", Total)
                intent.putExtra("addressLineOne", model.addressLineOne)
                intent.putExtra("addressLineTwo", model.addressLineTwo)
                intent.putExtra("city", model.city)
                intent.putExtra("province", model.province)
                intent.putExtra("postalCode", model.postalCode)
                Toast.makeText(this@ProfileActivity,
                    "You have selected a default address",
                    Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }
        }
    }
}




