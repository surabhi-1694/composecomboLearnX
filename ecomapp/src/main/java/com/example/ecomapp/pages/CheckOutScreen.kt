package com.example.ecomapp.pages

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ecomapp.Home.CategoryWiseProduct
import com.example.ecomapp.R
import com.example.ecomapp.signup.User
import com.example.ecomapp.utils.CommonVericalSpacer
import com.example.ecomapp.utils.calculateDiscount
import com.example.ecomapp.utils.calculateSubTotal
import com.example.ecomapp.utils.calculateTax
import com.example.ecomapp.utils.getUserDocument

@Composable
fun CheckOutScreen(modifier: Modifier,navController: NavController,productViewModel: ProductViewModel = viewModel()){
    val userModel = remember {
        mutableStateOf(User())
    }

    val uiState = productViewModel.uiState.collectAsState()

    // this will create list of CategoryWiseProduct with default initialization
//    val productList = remember {
//        mutableStateListOf(CategoryWiseProduct())
//    }

    // this will create list of Type CategoryWiseProduct with initialization of empty
     val productList = remember { mutableStateListOf<CategoryWiseProduct>() }

    val subTotal = remember {
        mutableFloatStateOf(0f)
    }

    val total = remember {
        mutableFloatStateOf(0f)
    }

    val discount = remember {
        mutableFloatStateOf(0f)
    }

    val tax = remember {
        mutableFloatStateOf(0f)
    }


    when(uiState.value){
       is ProductUiState.Loading->{
           //ui of loader
           Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
               CircularProgressIndicator()
           }
        }

        is ProductUiState.Error->{
            // ui of an error
            val message = (uiState as ProductUiState.Error).message
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error: $message", color = Color.Red)
            }
        }
        is ProductUiState.Success->{
            // get reponse from viewmodel and set ui of list
            val products = (uiState.value as ProductUiState.Success).products
            val userDoc = getUserDocument()
            userDoc.get().addOnCompleteListener { userTask ->
                val userResult = userTask.result.toObject(User::class.java)
                userResult?.let {
                    calculateSubTotal(
                        productList = products,
                        userModel = it
                    ){ subT ->
                        subTotal.floatValue = subT
                        Log.e("TAG_subT",subT.toString())
                        Log.e("TAG_subTotal",subTotal.floatValue.toString())
                        calculateDiscount(subTotal = subT,callback = { dis ->
                            discount.floatValue = dis

                        })
                        calculateTax(subTotal = subT, callback = { taxPrice ->
                            tax.floatValue = taxPrice
                        })
                        total.floatValue = subTotal.floatValue - discount.floatValue + tax.floatValue
                    }
                }
            }
        }
    }


    /*
    * here we get "cartItems " from "Users"
    * document since we need to display product details
    *
    * */
//    LaunchedEffect(Unit) {
//        val userDoc = getUserDocument()
//        userDoc.get().addOnCompleteListener {
//            if(it.isSuccessful){
//                val result = it.result.toObject(User::class.java)
//                if(result !=null){
//                    userModel.value = result
//                    //here we get all products of user and then make list of those products that exists in cartitems of user
//                    Firebase.firestore.collection("data")
//                        .document("stock")
//                        .collection("products")
//                        .whereIn("id",userModel.value.cartItems.keys.toList())
//                        .get().addOnCompleteListener{ tasks ->
//                            if(tasks.isSuccessful){
//                                val resultdata = tasks.result.toObjects(CategoryWiseProduct::class.java)
//                                productList.addAll(resultdata)
//                                calculateSubTotal(
//                                    productList = productList,
//                                    userModel = userModel){ subT ->
//                                    subTotal.floatValue = subT
//                                    Log.e("TAG_subT",subT.toString())
//                                    Log.e("TAG_subTotal",subTotal.floatValue.toString())
//
//                                    calculateDiscount(subTotal = subT,callback = { dis ->
//                                        discount.floatValue = dis
//
//                                    })
//                                    calculateTax(subTotal = subT, callback = { taxPrice ->
//                                        tax.floatValue = taxPrice
//                                    })
//                                    total.floatValue = subTotal.floatValue - discount.floatValue + tax.floatValue
//                                }
//                            }else{
//                                Log.e("TAG_EXEPTION",tasks.exception.toString())
//                            }
//                        }
//                }
//            }
//        }
//    }

    Column(modifier= modifier.fillMaxSize()
        .padding(top = 20.dp, start = 15.dp, end = 5.dp)) {
        Text(text = "CheckOut", style = TextStyle(fontSize = 18.sp))
        CommonVericalSpacer(16.dp)
        HorizontalDivider()
        Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(15.dp)) {
            Text(text = "SubTotal : ",style = TextStyle(fontWeight = FontWeight.Bold))
            Text(text = stringResource(R.string.rupee_symbol)+
                "${subTotal.floatValue}", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp))
        }
        Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(15.dp)) {
            Text(text = "discount : ",style = TextStyle(fontWeight = FontWeight.Bold))
            Text(text = stringResource(R.string.rupee_symbol)+"${discount.floatValue}", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp))
        }
        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(15.dp)) {
            Text(text = "Tax : ",style = TextStyle(fontWeight = FontWeight.Bold))
            Text(text = stringResource(R.string.rupee_symbol)+"${tax.floatValue}", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp))
        }
        CommonVericalSpacer(16.dp)
        HorizontalDivider()
        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(15.dp)) {
            Text(text = "Total : ",style = TextStyle(fontWeight = FontWeight.Bold,fontSize = 20.sp))
            Text(text = stringResource(R.string.rupee_symbol)+"${total.floatValue}", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp))
        }
        CommonVericalSpacer(10.dp)
    }
}