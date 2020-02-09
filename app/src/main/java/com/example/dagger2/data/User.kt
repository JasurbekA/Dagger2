package com.example.dagger2.data

import com.google.gson.annotations.SerializedName

data class Address(
    val street : String,
    val suite : String,
    val c : String,
    @SerializedName("zipcode")
    val zipCode : String,
    val geo : Geo
)

data class Geo(
    val lat : String,
    val lng : String

)

data class Company(
    val name : String,
    val catchPhrase : String,
    val bs : String
)

data class User(
    val id : Int,
    val name : String,
    val username : String,
    val email : String,
    val address : Address?,
    val phone : String,
    val website : String,
    val company : Company?
){
    constructor() : this(
        -1,
        "",
        "",
        "",
        null,
        "",
        "",
        null
    )
}
