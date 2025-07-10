package com.example.ecomapp.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecomapp.Home.CategoryWiseData
import com.example.ecomapp.signup.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

//todo get cart details of user
// /*
// user has cartitems , cart items has product id with qty
// here we need to display list of products that are added to cart
//so we get cart items related to user ; we will display list of products
// then we need to display quantity of product ; which we will get from productID
// */
@Composable
fun CartScreen(modifier: Modifier){
    var userModel = remember {
        mutableStateOf(User())
    }
    Column (modifier = Modifier.fillMaxSize().padding(top = 30.dp, start = 8.dp, end = 8.dp)){
        LaunchedEffect(key1 = Unit) {
            Firebase.firestore.collection("Users")
                .document(FirebaseAuth.getInstance().currentUser?.uid!!).get().addOnCompleteListener {
                    if(it.isSuccessful){
                        val userResult = it.result.toObject(User::class.java)
                        if(userResult!=null){
                            userModel.value = userResult
                        }
                    }
                }
        }
        Text(text = "Your Cart", textAlign = TextAlign.Start, style = TextStyle(fontSize = 20.sp,
            fontWeight = FontWeight.Bold ))

        LazyColumn{
            items(userModel.value.cartItems.toList()){(productId, qty)->
                CartListScreen(modifier= Modifier,productId = productId,qty =qty)
            }

        }
    }

}