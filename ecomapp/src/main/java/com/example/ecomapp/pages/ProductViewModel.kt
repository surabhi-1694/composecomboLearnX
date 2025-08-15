package com.example.ecomapp.pages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomapp.Home.CategoryWiseProduct
import com.example.ecomapp.signup.User
import com.example.ecomapp.utils.getUserDocument
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel: ViewModel() {

    private val _uiState = MutableStateFlow<ProductUiState>(ProductUiState.Loading)
    val uiState:StateFlow<ProductUiState> = _uiState
    init {
        fetchProducts()
    }
    private fun fetchProducts() {
        viewModelScope.launch {
            try {
                val userDoc = getUserDocument()
                userDoc.get().addOnCompleteListener { userTask ->
                    if(userTask.isSuccessful){
                        val userResult = userTask.result.toObject(User::class.java)
                        if(userResult !=null){
                            val cartIds = userResult.cartItems.keys.toList()
                            if (cartIds.isEmpty()) {
                                _uiState.value = ProductUiState.Success(emptyList())
                                return@addOnCompleteListener
                            }
                            //here we get all products of user and then make list of those products that exists in cartitems of user
                            Firebase.firestore.collection("data")
                                .document("stock")
                                .collection("products")
                                .whereIn("id",cartIds)
                                .get().addOnCompleteListener{ tasks ->
                                    if(tasks.isSuccessful){
                                        val resultdata = tasks.result.toObjects(CategoryWiseProduct::class.java)
                                        _uiState.value = ProductUiState.Success(resultdata.toList())
                                    }else{
                                        _uiState.value = ProductUiState.Error(tasks.exception?.message ?: "Error loading products")
                                    }
                                }
                        }else{
                            _uiState.value = ProductUiState.Error("User not found")
                        }
                    }else{
                        _uiState.value = ProductUiState.Error(userTask.exception?.message ?: "Error loading user")
                    }
                }

            }catch (ex:Exception){
                _uiState.value = ProductUiState.Error("Something went wrong")
            }

        }
    }

}

sealed class ProductUiState{
    object Loading : ProductUiState()
    data class Success(val products: List<CategoryWiseProduct>):ProductUiState()
    data class Error(val message:String):ProductUiState()

}