package com.example.ecomapp.Home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import coil3.compose.AsyncImage
import com.example.ecomapp.signup.AuthViewModel
import com.example.ecomapp.utils.CommonSpacer
import kotlinx.coroutines.launch

@Composable
fun Categoriesview(
    modifier: Modifier,
    authViewModel: AuthViewModel
) {

    var categoriesList = remember {
        mutableStateOf<List<CategoryData>>(emptyList())
    }
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    LaunchedEffect(Unit) {
        lifecycleScope.launch {
            val resultList: List<CategoryData> = authViewModel.getCategories().mapNotNull { doc ->
                doc.toObject(CategoryData::class.java)
            }
            Log.e("resultList_SIZE", resultList.size.toString())
            categoriesList.value = resultList
            Log.e("categoriesList_SIZE", categoriesList.value.size.toString())
        }

    }
    CommonSpacer(10.dp)
    Column {
        Text(text = "Categories",
            fontWeight = FontWeight.ExtraBold, fontSize = 30.sp)
        CommonSpacer(10.dp)
        LazyRow(horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(categoriesList.value) { item ->
                Card(
                    modifier = Modifier.size(120.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(10.dp)
                ) {
                    Column(modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center) {
                        AsyncImage(model = item.imageurl ,
                            contentDescription = "Categories Image",
                            modifier = modifier.size(90.dp)
                        )
                        Text(text = item.displayname,
                            style = TextStyle(textAlign = TextAlign.Center))


                    }

                }

            }
        }
    }



}