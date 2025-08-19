package com.example.ecomapp.Order

import com.example.ecomapp.Home.CategoryWiseProduct
import com.google.firebase.Timestamp

data class OrderItem(
    val id:String="",
    val userId:String="",
    val orderStatus:String="",
    val date: Timestamp = Timestamp.now(),
    val address:String="",
    val productItems:List<ProductWithQuantity> = emptyList()
)

data class ProductWithQuantity(
    val product: CategoryWiseProduct,
    val quantity: Long
)