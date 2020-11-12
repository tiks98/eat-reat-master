package com.example.eat_reat

class UserDetails {
    //declaring variables
    var id: String? = null
    var firstName: String? = null
    var lastName: String? = null
    var addressLineOne: String? = null
    var addressLineTwo: String? = null
    var city: String? = null
    var province: String? = null
    var postalCode: String? = null
    var email: String? = null

    //creating an empty constructor
    constructor()

    constructor(id: String, firstName: String, lastName: String, addressLineOne: String, addressLineTwo: String, city: String, province: String, postalCode: String, email: String){
        this.id = id
        this.firstName = firstName
        this.lastName = lastName
        this.addressLineOne = addressLineOne
        this.addressLineTwo = addressLineTwo
        this.city = city
        this.province = province
        this.postalCode = postalCode
        this.email = email
    }
}