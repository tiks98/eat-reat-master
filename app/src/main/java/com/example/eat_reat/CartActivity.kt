package com.example.eat_reat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.cart_item.*
import kotlinx.android.synthetic.main.cart_item.view.*
import kotlin.math.roundToInt


class CartActivity : AppCompatActivity() {

    var db = FirebaseFirestore.getInstance()
    private var adapter: CartAdapter? = null
    var curUser = Firebase.auth.currentUser!!.uid
    var subTotal = 0.00
    var foodId: String? = null
//    var itemImage = intent.extras?.getInt("itemImage")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        //setting up RecyclerView
        cartRecyclerView.layoutManager = LinearLayoutManager(this)


        val query = db.collection("cartItems").document(curUser).collection("cart").whereEqualTo("userId", Firebase.auth.currentUser?.uid)
//        val query = db.collection("cartItems").whereEqualTo(
//            "userId",
//            Firebase.auth.currentUser?.uid
//        )

        val options = FirestoreRecyclerOptions.Builder<FoodItems>().setQuery(
            query,
            FoodItems::class.java
        ).build()
        adapter = CartAdapter(options)
        cartRecyclerView.adapter = adapter


//        subTotal.toFloat()
//        totalPrice.text = subTotal.toString()


        checkoutButton.setOnClickListener{
            val item = db.collection("cartItems").document(curUser)
//            val item1 = db.collection("cartItems").whereArrayContains("foodItemId",item.toString())
                item.delete()
                    .addOnSuccessListener {
                               Toast.makeText(
                                   this,
                                   " All cart items are deleted from cart",
                                   Toast.LENGTH_LONG
                               ).show()
                           }
                    .addOnFailureListener {
                               Toast.makeText(
                                   this,
                                   " No cart items are deleted from cart",
                                   Toast.LENGTH_LONG
                               ).show()
                    }
            refresh()
        }


//        else throw NullPointerException()


    }


    fun refresh(){
        adapter!!.onDataChanged()
        adapter!!.startListening()
    }

    fun updateTotal() {
        refresh()
        totalPrice?.text = (Math.round(subTotal * 100.0) / 100.0).toString()
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

    private inner class CartViewHolder internal constructor(private val view: View): RecyclerView.ViewHolder(
        view
    ) {}


    private inner class CartAdapter internal constructor(options: FirestoreRecyclerOptions<FoodItems>) :
            FirestoreRecyclerAdapter<FoodItems, CartViewHolder>(options){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.cart_item,
                parent,
                false
            )
            return CartViewHolder(view)
        }

        override fun onBindViewHolder(holder: CartViewHolder, position: Int, model: FoodItems) {
            holder.itemView.cartItemNameTxtView.text = model.foodItemName
            holder.itemView.cartItemPrice.text = "$ "+model.foodItemPrice
//            var itemImage = intent.extras?.getInt(model.foodImage.toString())
//            if (model.foodImage != null) {
//                holder.itemView.cartItemImageView.setImageResource(itemImage!!)
//            }
//            else throw NullPointerException()



            var price = model.foodItemPrice?.toFloat()
            var quantity: Int? = 1


            holder.itemView.incrementBtn.setOnClickListener{
                if (price != null) {
                    if (quantity != null){
                        quantity =  quantity!! +1
                        holder.itemView.quantity_text_view.text = quantity.toString()
                        holder.itemView.cartItemPrice.text = "$ ${(Math.round(subTotal * 100.0) / 100.0)}"
                        subTotal += (price * quantity!!)
                        updateTotal()
                    }
            //                holder.itemView.quantity_text_view.text = quantity.toString()
            //                quantity?.inc()

                    else throw NullPointerException()
                }
            }
            holder.itemView.decrementBtn.setOnClickListener{
                if (quantity != null) {
                    if (price != null) {
                        if (quantity!! > 0) {
                            quantity = quantity!! - 1
                            holder.itemView.quantity_text_view.text = quantity.toString()
                            holder.itemView.cartItemPrice.text = "$ ${(Math.round(subTotal * 100.0) / 100.0)}"
                            subTotal -= (price * quantity!!) //change this logic
                            updateTotal()
                        } else
                            throw NullPointerException()
                    }
                }
            }

            if (price != null && subTotal != null) {
                subTotal += (price * quantity!!)
                updateTotal()
                refresh()
            }
            else throw NullPointerException()



            if(model.foodItemId != null){
               foodId = model.foodItemId.toString()
            }
            else throw NullPointerException()



            if (foodId != null) {
                holder.itemView.deleteCartItem.setOnClickListener {
//                    val item = db.collection("cartItems").whereEqualTo("foodItemId", foodId!!)
                    val reference = db.collection("cartItems").document(curUser).collection("cart").document("$foodId")
                    if (reference != null) {
                        reference.delete()
                            .addOnSuccessListener {
                           Toast.makeText(
                               this@CartActivity,
                               " ${model.foodItemName} is successfully deleted from cart",
                               Toast.LENGTH_LONG
                           ).show()
                                updateTotal()
                       }
                       .addOnFailureListener {
                           Toast.makeText(
                               this@CartActivity,
                               " ${model.foodItemName} is not successfully deleted, Please try again",
                               Toast.LENGTH_LONG
                           ).show()
                       }
                    } else throw NullPointerException()
                    refresh()
                }
            }

//            if (subTotal != null) {
//                totalPrice?.text = subTotal.toString().trim()
//                refresh()
//            }

//            val deleteCartItem = findViewById<Button>(R.id.deleteCartItem)
//
//            deleteCartItem.setOnClickListener{
//              val item =  db.collection("cartItems").whereEqualTo("foodItemId", model.foodItemId!!)
//                if (item != null){
//                    db.document(item.toString()).delete()
//                }
//                else throw NullPointerException()
//            }

//            deleteCartItem.setOnClickListener{
////                Toast.makeText(
////                               this@CartActivity,
////                               " ${model.foodItemName} is successfully deleted from cart",
////                               Toast.LENGTH_LONG
////                           ).show()
//                if (model.foodItemId != null) {
//                    db.collection("cartItems")
//                        .document(holder.itemView.cartItemNameTxtView.text.toString())
//                        .delete()
//                }
////                    .addOnSuccessListener {
////                           Toast.makeText(
////                               this@CartActivity,
////                               " ${model.foodItemName} is successfully deleted from cart",
////                               Toast.LENGTH_LONG
////                           ).show()
////                       }
////                       .addOnFailureListener {
////                           Toast.makeText(
////                               this@CartActivity,
////                               " ${model.foodItemName} is not successfully deleted, Please try again",
////                               Toast.LENGTH_LONG
////                           ).show()
////                       }
////               val query =  db.collection("cartItems").whereEqualTo("foodItemName", holder.itemView.cartItemNameTxtView.text)
////               if (query ==db.collection("cartItems").document(model.foodItemName!!)) {
////
////                   db.collection("cartItems").document(model.foodItemId!!)
////                       .delete()
////                       .addOnSuccessListener {
////                           Toast.makeText(
////                               this@CartActivity,
////                               " ${model.foodItemName} is successfully deleted from cart",
////                               Toast.LENGTH_LONG
////                           ).show()
////                       }
////                       .addOnFailureListener {
////                           Toast.makeText(
////                               this@CartActivity,
////                               " ${model.foodItemName} is not successfully deleted, Please try again",
////                               Toast.LENGTH_LONG
////                           ).show()
////                       }
//////                    .whereEqualTo("foodItemName", holder.itemView.cartItemNameTxtView.text)
////               }
//            }


        }



    }


}





    
