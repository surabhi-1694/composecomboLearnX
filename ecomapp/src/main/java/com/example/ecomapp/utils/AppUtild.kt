package com.example.ecomapp.utils

import android.content.Context
import androidx.compose.ui.res.stringResource
import com.example.ecomapp.R
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.firestore


//here we are updating user document/
// *
// why: because we already created user in sign up
// and now when user add an item to cart; we will update
// user document with cart item as every user has one cart item
// */
fun addToCart(context: Context,productId:String){
   val  userDoc:DocumentReference =
       Firebase.firestore.collection("Users").document(FirebaseAuth.getInstance().currentUser?.uid!!)

    //updateCurrentUser

    //here we get userDoc as DocumentReference
    // get cartItem data from user object
    userDoc.get().addOnCompleteListener {
        if(it.isSuccessful){
            val currentCart =
                it.result.get("cartItems") as? Map<String,Long>?:emptyMap()
             val currentQuantity = currentCart[productId]?:0

            val updateQuantity = currentQuantity + 1

            //why prefix? 
            val updatedCart = mapOf("cartItems.$productId" to updateQuantity)
            userDoc.update(updatedCart).addOnCompleteListener{
                if(it.isSuccessful){
                    ShowToast(context, "Item has added in Cart")
                }else{
                    ShowToast(context, "Failed adding item  in Cart")

                }
            }


        }
    }

}