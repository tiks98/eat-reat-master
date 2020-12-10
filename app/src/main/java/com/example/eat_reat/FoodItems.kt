package com.example.eat_reat

import android.media.Image

class FoodItems {

    var foodItemId: String? = null
    var userId: String? = null
    var foodItemName: String? = null
    var foodItemPrice: String? = null
    var foodQuantity: Int? = null
    var foodImage: Int? = null

    constructor()

    constructor(foodItemId: String, userId: String, foodItemName: String, foodItemPrice: String, foodQuantity: Int, foodImage: Int){
        this.foodItemId = foodItemId
        this.userId = userId
        this.foodItemName = foodItemName
        this.foodItemPrice = foodItemPrice
        this.foodQuantity = foodQuantity
        this.foodImage = foodImage
    }
}