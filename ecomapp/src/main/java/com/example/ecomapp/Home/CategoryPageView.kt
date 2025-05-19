package com.example.ecomapp.Home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ecomapp.signup.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun CategoryPageView(categoryId:String,authViewModel: AuthViewModel = viewModel()){

    val productList = remember {
        mutableStateOf<List<CategoryWiseData>>(emptyList())
    }

    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    LaunchedEffect(Unit) {
        lifecycleScope.launch {
            productList.value =  authViewModel.getCategoriesListwise()

//            val catWiseProductList : List<CategoryWiseData> = authViewModel.getCategoryWiseProduct(categoryId).mapNotNull { doc ->
//                doc.toObject(CategoryWiseData::class.java)
//            }
//            productList.value = catWiseProductList
        }

    }
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(productList.value){ item ->
                Text(text = item.title)
            }
        }
    }

}