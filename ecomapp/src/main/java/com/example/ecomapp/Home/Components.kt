package com.example.ecomapp.Home

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import coil3.compose.AsyncImage
import com.example.ecomapp.R
import com.example.ecomapp.pages.ProductDetailActivity
import com.example.ecomapp.signup.AuthViewModel
import com.example.ecomapp.utils.CommonHorizontalSpacer
import com.example.ecomapp.utils.CommonVericalSpacer
import com.example.ecomapp.utils.ShowToast
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.ShiftIndicatorType
import kotlinx.coroutines.launch

//https://developer.android.com/develop/ui/compose/quick-guides/collections/display-images?hl=en


@Composable
 fun HeaderView(modifier: Modifier, authViewModel: AuthViewModel) {
    var name by remember {
        mutableStateOf("")
    }
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    LaunchedEffect(Unit) {
        //why to use this to use coroutine scopes :-> compose is concept of creating UI
        // so we can not use any coroutine or any network
        // call or showing toast that is why in order to do all above we have to go for sideeffects like launchedEffect so that we can use show toast or coroutine etc...
        lifecycleScope.launch {
            name = authViewModel.getUserName().toString()
        }
    }
        Row(modifier = Modifier.padding(12.dp),
            horizontalArrangement = Arrangement.End) {
            Column(modifier = Modifier.weight(8f)) {
                Text(text = "Welcome Back",
                    modifier = Modifier.fillMaxWidth().padding(0.dp),
                    textAlign = TextAlign.Start,
                    color = Color.Blue,
                    fontSize = 30.sp,
                    style = TextStyle(fontFamily = FontFamily.Cursive, fontWeight = FontWeight.ExtraBold)
                )
                CommonVericalSpacer(10.dp)
                Text(text = name
                    ,modifier = Modifier.padding(10.dp),
                    textAlign = TextAlign.Left,
                    color = Color.Blue,
                    fontSize = 20.sp,
                    style = TextStyle(fontFamily = FontFamily.Cursive, fontWeight = FontWeight.Bold
                    )
                )
            }
            IconButton(onClick = {
            },modifier = Modifier.padding(0.dp)) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            }
        }

}

@Composable
fun BannerView(modifier: Modifier = Modifier,authViewModel: AuthViewModel){
    var bannerUrl by remember {
        mutableStateOf<List<String>>(emptyList())
    }
    var pageState =  rememberPagerState (0){
        bannerUrl.size
    }
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    LaunchedEffect(Unit) {
        //why to use this to use coroutine scopes :-> compose is concept of creating UI
        // so we can not use any coroutine or any network
        // call or showing toast that is why in order to do all above we have to go for sideeffects like launchedEffect so that we can use show toast or coroutine etc...
        lifecycleScope.launch {
            bannerUrl = authViewModel.getBannerURL()
            Log.e("Banner URL", bannerUrl.size.toString())
        }
    }
    Column(modifier = modifier) {
        //here we use horizontal pager so that we can display banner images
        //horizontalpager contains composable that tell us that we need to set ui for each page and it's state indicate page index
        HorizontalPager(state =  pageState,
            pageSpacing = 24.dp,
            ) {
                AsyncImage(
                    model = if(bannerUrl.isNotEmpty()) bannerUrl.get(it)else "",
                    contentDescription = "BannerURL",
                    modifier = Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp)),
                )
            }
        CommonVericalSpacer(5.dp)
        DotsIndicator(
            dotCount = bannerUrl.size,
            type = ShiftIndicatorType(dotsGraphic =
                DotGraphic(color = MaterialTheme.colorScheme.primary, size = 6.dp)
            ),
            pagerState = pageState,
        )

    }

}

@Composable
fun productListView(modifier: Modifier = Modifier,item:CategoryWiseData){
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxWidth()) {
        Card(modifier=Modifier.fillMaxWidth().clickable {
            //check
            val intent = Intent(context,ProductDetailActivity::class.java)
            intent.putExtra("productITem",item)
            context.startActivity(intent)
        },
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(5.dp)) {
            Column(modifier = modifier.fillMaxWidth().padding(10.dp)) {
                Text(text = item.title,modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                CommonVericalSpacer(10.dp)
                AsyncImage(model = item.imageUrls.firstOrNull(),
                    contentDescription = "product Image",
                    modifier = Modifier.fillMaxWidth().height(100.dp)
                        .clip(RoundedCornerShape(15.dp)))
                CommonVericalSpacer(5.dp)
                Text(text = item.description,
                    style = TextStyle(fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
                CommonVericalSpacer(5.dp)
                Row(modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                    Text(text = "\u20B9${item.price}",
                        style = TextStyle(fontSize = 12.sp,
                            fontWeight = FontWeight.Bold)
                    )
                    Text(text = "\u20B9${item.actualPrice}",
                        style = TextStyle(fontSize = 14.sp,
                            fontWeight = FontWeight.Normal, textDecoration = TextDecoration.LineThrough))
                    IconButton(onClick = {
                        //move to another activity
                        ShowToast(context,"Add to Cart")
                    }, modifier = Modifier.padding(0.dp)) {
                        Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "add to cart")
                    }
                }
            }

        }
    }


}