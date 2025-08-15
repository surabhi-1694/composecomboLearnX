package com.example.ecomapp.pages

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ecomapp.CheckoutRoute
import com.example.ecomapp.GlobalNavigator
import com.example.ecomapp.Home.CategoryWiseProduct
import com.example.ecomapp.R
import com.example.ecomapp.signup.User
import com.example.ecomapp.utils.CommonButton
import com.example.ecomapp.utils.CommonVericalSpacer
import com.example.ecomapp.utils.PaymentListener
import com.example.ecomapp.utils.calculateDiscount
import com.example.ecomapp.utils.calculateSubTotal
import com.example.ecomapp.utils.calculateTax
import com.example.ecomapp.utils.getUserDocument

@Composable
fun CheckOutScreen(modifier: Modifier,paymentInterface:PaymentListener,navController: NavController,productViewModel: ProductViewModel = viewModel()){
    var userModel = remember {
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


    /*
    * here we get "cartItems " from "Users"
    * document since we need to display product details
    *
    * */
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
            // get response from viewmodel and set ui of list
            val products = (uiState.value as ProductUiState.Success).products
            val userDoc = getUserDocument()
            userDoc.get().addOnCompleteListener { userTask ->
                val userResult = userTask.result.toObject(User::class.java)
                if (userResult != null) {
                    userModel.value = userResult
                }
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

    Column(modifier= modifier.fillMaxSize()
        .padding(top = 20.dp, start = 15.dp, end = 5.dp)) {
        Text(text = "CheckOut", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
        CommonVericalSpacer(16.dp)
        HorizontalDivider()
        Text(modifier = modifier, text = "Deliver To: ", style = TextStyle(fontWeight = FontWeight.Bold))
        Text(modifier = modifier, text = userModel.value.address )
        Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(15.dp)) {
            Text(text = "SubTotal : ",style = TextStyle(fontWeight = FontWeight.Bold))
            Text(text = stringResource(R.string.rupee_symbol)+
                "${subTotal.floatValue}", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp))
        }
        Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(15.dp)) {
            Text(text = "discount (-): ",style = TextStyle(fontWeight = FontWeight.Bold))
            Text(text = stringResource(R.string.rupee_symbol)+"${discount.floatValue}", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp))
        }
        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(15.dp)) {
            Text(text = "Tax (+): ",style = TextStyle(fontWeight = FontWeight.Bold))
            Text(text = stringResource(R.string.rupee_symbol)+"${tax.floatValue}", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp))
        }
        CommonVericalSpacer(16.dp)
        HorizontalDivider()
        Text(text = "To Pay: ",modifier = modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            Row(modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                Text(text = "Total : "
                    ,style = TextStyle(fontWeight = FontWeight.Bold,fontSize = 20.sp))
                Text(text = stringResource(R.string.rupee_symbol)+"${total.floatValue}", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp))
        }
        CommonVericalSpacer(10.dp)
        Button(onClick = {
            //redirect to razor pay // manage success failure
            /* we will create simple interface for success failure just to redirect from page
            // in real time scenario razorpay will provide success failure from it's dependancy's paymentlistenr method
            * */
            //for testing purpose we can either call success or failure
            paymentInterface.paymentSuccess()
        }, modifier = Modifier.fillMaxWidth().padding(10.dp)) {
            Text(text = "Pay Now")
        }
    }
}