package com.example.ecomapp.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.ecomapp.Home.CategoryWiseData
import com.example.ecomapp.signup.User
import com.example.ecomapp.utils.CommonHorizontalSpacer
import com.example.ecomapp.utils.CommonVericalSpacer
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
fun CartListScreen(modifier: Modifier,productId:String,qty:Long){
    val product = remember {
        mutableStateOf(CategoryWiseData())
    }

    LaunchedEffect(key1 = Unit) {
        Firebase.firestore.collection("data")
            .document("stock")
            .collection("products")
            .document(productId).get()
            .addOnCompleteListener {
                if(it.isSuccessful){
                    val result = it.result.toObject(CategoryWiseData::class.java)
                    if (result != null) {
                        product.value = result
                    }
                }
            }
    }

    Card(modifier = Modifier.fillMaxWidth().padding(8.dp),
        colors = CardDefaults.cardColors(Color.LightGray),
        elevation = CardDefaults.cardElevation(10.dp)) {
        Row(modifier = modifier.fillMaxWidth().padding(10.dp),
            verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(model = product.value.imageUrls.firstOrNull(),
                contentDescription = "product Image",
                modifier = Modifier.size(60.dp).clip(RoundedCornerShape(3.dp))
            )
            CommonHorizontalSpacer(5.dp)
            Column(modifier = Modifier.fillMaxWidth().weight(1f).padding(8.dp), verticalArrangement = Arrangement.Center){
                Text(text = product.value.title)
                CommonVericalSpacer(5.dp)
                Text(text = product.value.price)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = {

                    }, colors =
                        IconButtonDefaults
                            .filledIconButtonColors
                                (containerColor = Color.Blue, contentColor = Color.White)) {
                        Text(text = "-")
                    }
                    Text(text = "$qty")
                    IconButton(onClick = {

                    },colors =
                        IconButtonDefaults
                            .filledIconButtonColors
                                (containerColor = Color.Blue, contentColor = Color.White)) {
                        Text(text = "+")
                    }

                }
            }
            IconButton(onClick = {
            }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "cart Delete")
            }
        }
    }

}