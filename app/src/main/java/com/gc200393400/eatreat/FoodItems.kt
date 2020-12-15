package com.gc200393400.eatreat

//reference: https://github.com/ifotn/comp3025-restorater-f20

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