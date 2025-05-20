package com.example.ecomapp.Home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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

    var productimageUrls by remember {
        mutableStateOf<List<String>> (emptyList())
    }

    val pagerState =  rememberPagerState (0){
        productimageUrls.size
    }

    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    LaunchedEffect(Unit) {
        lifecycleScope.launch {
//            productList.value =  authViewModel.getCategoriesListwise(categoryId)
            val catWiseProductList : List<CategoryWiseData> = authViewModel.getCategoryWiseProduct(categoryId).mapNotNull { doc ->
                doc.toObject(CategoryWiseData::class.java)
            }
            productList.value = catWiseProductList
                .plus(catWiseProductList).plus(catWiseProductList).plus(catWiseProductList)
        }
    }

    LazyVerticalGrid(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(10.dp)) {
            Log.e("productList ",productList.value.size.toString())
            itemsIndexed(productList.value){index, item ->
                productListView(modifier = Modifier,item)
            }
        }

}