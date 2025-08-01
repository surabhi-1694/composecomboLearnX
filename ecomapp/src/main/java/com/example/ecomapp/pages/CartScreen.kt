package com.example.ecomapp.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecomapp.CheckoutRoute
import com.example.ecomapp.GlobalNavigator
import com.example.ecomapp.signup.User
import com.example.ecomapp.utils.getUserDocument
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
    Column (modifier =
        modifier.fillMaxSize().padding(top = 0.dp, start = 8.dp, end = 0.dp)){
        DisposableEffect (key1 = Unit) {
            val userDoc = getUserDocument()
           val listener =  userDoc
                // use to verify task complete or not
                // usage: if it is one time perform task if task's value/subvalue keep update on some buton trigger use snapshotlistenr it will get latest snapshot that is update data
//                .get().addOnCompleteListener {
                .addSnapshotListener { it, error ->
                    if(it!=null){
                        val userResult = it.toObject(User::class.java)
                        if(userResult!=null){
                            userModel.value = userResult
                        }
                    }
                }
            onDispose {
                listener.remove()
            }
        }
        Text(text = "Your Cart",
            modifier = Modifier.padding(1.dp),
            textAlign = TextAlign.Start,
            style = TextStyle(fontSize = 20.sp,
            fontWeight = FontWeight.Bold ))

//        Items have a natural unique ID (like id, productId, etc.)
//        Think of key in Compose like getItemId() in RecyclerView.Adapter with setHasStableIds(true).
//        Both help the framework track items intelligently during data changes.

//        Assuming cartItems is a Map<ProductId, Quantity>,
//        then when you call .toList(), it becomes:
//        List<Pair<ProductId, Quantity>>
//        So now each item (it) is a Pair, like:
//        ("ABC123", 2)
//        In a Pair<A, B>:
//        it.first → "ABC123" (the productId)
//        it.second → 2 (the quantity)
//        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.weight(1f)){
                items(userModel.value.cartItems.toList(),
                    key ={it.first}) {(productId,qty)->
        CartListScreen(modifier= Modifier,productId = productId,qty =qty)
                }
            }
            Button(onClick = {
                GlobalNavigator.navController.navigate(CheckoutRoute)
            }, modifier = Modifier.fillMaxWidth().padding(10.dp)) {
                Text(text = "Checkout")
            }
    }

}