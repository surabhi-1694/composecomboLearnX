package com.example.ecomapp.Order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomapp.Home.CategoryWiseProduct
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class OrderViewModel:ViewModel() {
    val _uiState = MutableStateFlow<OrderUiState>(OrderUiState.Loading)
    //this need to observe in outside class
    val uiState: StateFlow<OrderUiState> = _uiState


    init {
        getOrderList()
    }

    private fun getOrderList(){
        viewModelScope.launch {
            val orderResultList:List<OrderModel> =  getOrders().mapNotNull { doc->
                doc.toObject(OrderModel::class.java)
            }

            // Collect all unique productIds across all orders
            val allProductIds = orderResultList.flatMap { it.orderItems.keys }.distinct()
            if(allProductIds.isNotEmpty()){
                //check for allProductIds  empty array

                Firebase.firestore.collection("data")
                    .document("stock")
                    .collection("products")
                    .whereIn("id",allProductIds)
                    .get().addOnCompleteListener { product->
                        if(product.isSuccessful){
                            val products = product.result.toObjects(CategoryWiseProduct::class.java)
                            if (products.isNotEmpty()) {
                                //associatedBy will create map with key in this case is and if find out repeat id it will consider the last one
                                val productMap: Map<String, CategoryWiseProduct> = products.associateBy { it.id }

                                val orderWithProductList = orderResultList.map { order->
                                    val productList = order.orderItems.mapNotNull { (prodId, qty) ->
                                        productMap[prodId]?.let { product ->
                                            ProductWithQuantity(product, qty)
                                        }
                                    }
                                    OrderItem(id = order.id,
                                        userId = order.userId,
                                        orderStatus = order.orderStatus,
                                        date = order.date,
                                        address = order.address,
                                        productItems = productList)

                                }
                                _uiState.value = OrderUiState.Success(orderWithProductList)

                            }else{
                                _uiState.value = OrderUiState.Error("No Product Found.")
                            }
                        }else{
                            _uiState.value = OrderUiState.Error("Something went wrong.")
                        }
                    }
            }


        }

    }

    suspend fun getOrders():MutableList<DocumentSnapshot>{
        val orderList = Firebase.firestore.collection("order").whereEqualTo("userId", FirebaseAuth.getInstance().currentUser?.uid!!).get().await()
        return orderList.documents
    }
}

sealed class OrderUiState{
    object Loading : OrderUiState()
    data class Success(val orders: List<OrderItem>):OrderUiState()
    data class Error(val message:String):OrderUiState()

}