package com.example.ecomapp.Order

import com.google.firebase.Timestamp

data class OrderModel(
    val id:String="",
    val userId:String="",
    val orderStatus:String="",
    val date:Timestamp = Timestamp.now(),
    val address:String="",
    val orderItems:Map<String,Long> = emptyMap()
)
