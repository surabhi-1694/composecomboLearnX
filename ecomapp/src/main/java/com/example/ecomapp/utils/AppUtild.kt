package com.example.ecomapp.utils

import android.content.Context
import com.example.ecomapp.Home.CategoryWiseProduct
import com.example.ecomapp.signup.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore




fun calculateSubTotal(
    productList: List<CategoryWiseProduct>,
    userModel: User,
    callback: (Float) -> Unit
) {
    var subtotal = 0f
    productList.forEach {
       val  qty = userModel.cartItems[it.id]?:0
        subtotal +=  it.actualPrice.toLong() * qty
    }
    callback(subtotal)
}

fun calculateDiscount(subTotal:Float,callback: (Float) -> Unit){
    callback((subTotal * (getDiscountPercentage()/100f)))
}

fun calculateTax(subTotal:Float,callback: (Float) -> Unit){
    callback((subTotal * (getTaxPercentage()/100f)))
}

fun getDiscountPercentage():Float{
    return 15.0f
}

fun getTaxPercentage():Float{
    return 8.0f
}

fun getUserDocument(): DocumentReference {
     val  userDoc =
        Firebase.firestore.collection("Users")
            .document(FirebaseAuth.getInstance()
                .currentUser?.uid!!)

    return userDoc
}
//here we are updating user document/
// *
// why: because we already created user in sign up
// and now when user add an item to cart; we will update
// user document with cart item as every user has one cart item
// */
fun addToCart(context: Context,productId:String){
//   val  userDoc:DocumentReference =
//       Firebase.firestore.collection("Users")
//           .document(FirebaseAuth.getInstance()
//               .currentUser?.uid!!)

    val userDoc = getUserDocument()
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

fun removeFromCart(context: Context,productId: String,isRemoveAll:Boolean = false){
    val userDoc = getUserDocument()

    userDoc.get().addOnCompleteListener{
        if(it.isSuccessful){
            val currentCart = it.result.get("cartItems")as? Map<String,Long>?: emptyMap()
            //we will have list of cartItems here that is why we need to get one data from list that is of productid
            val  currentQuantity = currentCart[productId]?:0

            val updatedQuantity = currentQuantity - 1
            val updateCart =
            if(updatedQuantity <= 0 || isRemoveAll){
                mapOf("cartItems.$productId" to FieldValue.delete())
            }else{
                   mapOf("cartItems.$productId" to updatedQuantity)
            }
            userDoc.update(updateCart).addOnCompleteListener{
                if(it.isSuccessful){
                    ShowToast(context, "Item has been removed from Cart")
                }else{
                    ShowToast(context, "Failed removing item  from Cart")
                }
            }


        }
    }

}