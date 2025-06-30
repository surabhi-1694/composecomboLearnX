package com.example.ecomapp.Home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryWiseData(
    val id:String="",
    val title:String="",
    val price:String="",
    val actualPrice:String = "",
    val description:String="",
    //work as ref. key for category
    val categoryId:String="",
    val imageUrls:List<String> = emptyList(),
    val otherDetails:Map<String,String> = emptyMap()
):Parcelable
