package com.example.ecomapp.Home

data class CategoryWiseData(
    val id:String="",
    val title:String="",
    val price:String="",
    val actualPrice:String = "",
    val description:String="",
    //work as ref. key for category
    val categoryId:String="",
    val imageUrls:List<String> = emptyList()
)
