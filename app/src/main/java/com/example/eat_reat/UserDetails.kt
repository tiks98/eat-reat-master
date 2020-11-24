package com.example.eat_reat

class UserDetails {
    //declaring variables
    var addressId: String? = null
    var userId: String? = null
    var addressLineOne: String? = null
    var addressLineTwo: String? = null
    var city: String? = null
    var province: String? = null
    var postalCode: String? = null


    //creating an empty constructor
    constructor()

    constructor(addressId: String, userId: String,addressLineOne: String, addressLineTwo: String, city: String, province: String, postalCode: String){
        this.addressId = addressId
        this.userId = userId
        this.addressLineOne = addressLineOne
        this.addressLineTwo = addressLineTwo
        this.city = city
        this.province = province
        this.postalCode = postalCode

    }
}