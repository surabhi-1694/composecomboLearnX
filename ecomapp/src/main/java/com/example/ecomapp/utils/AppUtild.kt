package com.example.ecomapp.utils

import android.content.Context
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

fun addToCart(context: Context,productId:String){
    Firebase.firestore.collection("users").document()
}