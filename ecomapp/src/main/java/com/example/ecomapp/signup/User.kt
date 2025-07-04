package com.example.ecomapp.signup


/*
* we have cartitems has mapinglist here :
* why: becuase one user have on cartitem , one cart item can have multiple products
* and these products can have multiple quantity
* so here we will use product id as key and quantity as values
* so that we can add multiple product with same id with different quantity
* */
data class User(
    val name :String,
    val email:String,
    val uid:String,
    val cartItems:Map<String,Long> = emptyMap()
)
