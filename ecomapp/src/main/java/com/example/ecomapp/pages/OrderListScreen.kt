package com.example.ecomapp.pages
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.ecomapp.Order.OrderItem
import com.example.ecomapp.Order.OrderModel
import com.example.ecomapp.Order.OrderUiState
import com.example.ecomapp.Order.OrderViewModel
import com.example.ecomapp.R
import com.example.ecomapp.utils.CommonHorizontalSpacer

@Composable
fun OrderListScreen(modifier: Modifier,navController: NavController,orderViewModel: OrderViewModel = viewModel()){

    val orderList =  remember {
        mutableStateOf<List<OrderItem>>(emptyList())
    }

    val uiStateValue = orderViewModel.uiState.collectAsState()

    when(uiStateValue.value){
        is OrderUiState.Loading->{

        }
        is OrderUiState.Success ->{
            orderList.value = (uiStateValue.value as OrderUiState.Success).orders
        }
        is OrderUiState.Error ->{

        }
    }
        Column(modifier = modifier.fillMaxWidth().padding(top = 10.dp, start = 10.dp, end = 5.dp, bottom = 0.dp)) {
            Text(text = "My Orders ",
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp)
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(orderList.value, key = {it.id}){ orderItem->
                        Column(modifier =  Modifier.fillMaxWidth()) {
                            orderItem.productItems.forEach { productItems->
                                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp)) {

                                    Column(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
                                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                                            AsyncImage(model = productItems.product.imageUrls[0], contentDescription = "prodcut image",modifier = Modifier.size(90.dp))
                                            Text(productItems.product.title)

                                        }
                                        CommonHorizontalSpacer(5.dp)
                                        Row {
                                            Text("Price:")
                                            Text("\u20B9${productItems.product.price}")
                                        }

                                        Text(orderItem.orderStatus, style = TextStyle(fontWeight = FontWeight.Bold, color = colorResource(
                                            R.color.grdient_green3)))

                                    }
                                }

                            }
                        }

                }
            }
        }
}