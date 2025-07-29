package com.example.ecomapp.pages

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ecomapp.Home.CategoryWiseProduct
import com.example.ecomapp.signup.User
import com.example.ecomapp.utils.getUserDocument
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

@Composable
fun CheckOutScreen(modifier: Modifier,navController: NavController){
    val userModel = remember {
        mutableStateOf(User())
    }
    val productList = remember {
        mutableStateListOf(CategoryWiseProduct())
    }

    /*
    * here we get "cartItems " from "Users"
    * document since we need to display product details
    *
    * */
    LaunchedEffect(Unit) {
        val userDoc = getUserDocument()
        userDoc.get().addOnCompleteListener {
            if(it.isSuccessful){
                val result = it.result.toObject(User::class.java)
                if(result !=null){
                    userModel.value = result
                    Firebase.firestore.collection("data")
                        .document("stock")
                        .collection("products")
                        .whereIn("id",userModel.value.cartItems.keys.toList())
                        .get().addOnCompleteListener{ tasks ->
                            if(tasks.isSuccessful){
                                val resultdata = tasks.result.toObjects(CategoryWiseProduct::class.java)
                                productList.addAll(resultdata)
                                Log.e("productList_size",productList.size.toString())
                            }else{
                                Log.e("TAG_EXEPTION",tasks.exception.toString())
                            }
                        }
                }
            }
        }
    }

    Column(modifier= modifier.fillMaxSize()
        .padding(top = 20.dp, start = 5.dp, end = 5.dp)) {
        Text(text = "Welcome To final destination")
            Text(text = productList.toList().toString() )
    }
}