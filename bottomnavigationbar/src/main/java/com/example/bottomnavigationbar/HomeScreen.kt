package com.example.bottomnavigationbar

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.kwabenaberko.newsapilib.models.Article
import com.kwabenaberko.newsapilib.models.Source

@Composable
fun HomeScreen(navController: NavController,dataModel: newsDataModel) {
    val articleSources by dataModel.articlesSource.observeAsState(emptyList())
    val articlevatSources by dataModel.categoryWiseNews.observeAsState(emptyList())
    val everyArticleSources by dataModel.everyArticlesSource.observeAsState(emptyList())
        Column(modifier = Modifier.fillMaxSize()) {
            Text(modifier = Modifier.padding(5.dp), text = "News App ",
                fontWeight = FontWeight.Bold, fontSize = 25.sp)

            //horizontal list github
                categoryList(dataModel)

            Sourceslist(articlevatSources,navController)
            Spacer(modifier = Modifier.height(5.dp))
            Divider(color = Color.Gray, thickness = 2.dp)
            Spacer(modifier = Modifier.height(5.dp))
            Everyarticlelist(everyArticleSources,navController)
        }
    }



    @Composable
fun Everyarticlelist(everyArticleSources: List<Article>, navController: NavController) {

    Column(modifier = Modifier.fillMaxHeight()) {
        if(everyArticleSources.size>0){
            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                itemsIndexed(everyArticleSources){index: Int, item: Article ->
                    Log.e("TAG ",item.title)
                    Log.e("TAG ",item.url)
                    Log.e("TAG ",item.description)

                    Card(modifier = Modifier.padding(5.dp),
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onTertiary),
                        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp, hoveredElevation = 20.dp)) {
                        Row(modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center) {
                            Spacer(modifier = Modifier.height(0.dp))
                            AsyncImage(modifier = Modifier.size(120.dp).padding(5.dp)
                                .clip(CircleShape),
                                contentScale = ContentScale.Crop,
                                model = item.urlToImage,
                                contentDescription = "Article Image")
                            Spacer(modifier = Modifier.height(5.dp))
                            Column(modifier = Modifier.padding(10.dp)) {
                                if(item.author !=null){
                                    Text(text = item.author,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 25.sp,
                                        color = Color.Black)
                                }else{
                                    Log.e("NEWSAPI","AUTHOR NULL")
                                }
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(text = item.description,
                                    color = Color.Gray,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp)
                                Spacer(modifier = Modifier.height(5.dp))

                            }
                        }
                    }

                }
            }
        }else{
            Text(modifier = Modifier.fillMaxWidth(), text = "NO DATA FOUND", textAlign = TextAlign.Center)

        }
    }
            }

@Composable
fun Sourceslist(articleSources: List<Source>, navController: NavController) {
    Column(modifier = Modifier.fillMaxHeight(0.5f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        if(articleSources.size>0){
            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                itemsIndexed(articleSources){ index: Int, item: Source ->
                    Card(modifier = Modifier.padding(5.dp),
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onTertiary),
                        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp, hoveredElevation = 20.dp)) {
                        Column(modifier = Modifier.fillMaxSize().padding(5.dp).clickable {
                            navController.navigate(ScreenList(name = item.name,
                                age = 0,
                                address = item.description))
                        }) {
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(text = item.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 25.sp,
                                color = Color.Blue)
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(text =  item.url,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = Color.Blue
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(text = item.country,
                                color = Color.Blue,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp)
                            Spacer(modifier = Modifier.height(5.dp))
                        }
                    }


                }

            }
        }else{
            Text(modifier = Modifier.fillMaxWidth(), text = "NO DATA FOUND", textAlign = TextAlign.Center)

        }
    }

}

@Composable
fun categoryList(dataModel: newsDataModel) {

    var  isSearchSelected by remember {
        mutableStateOf(false)
    }
    var searchString by remember {
        mutableStateOf("")
    }
    val categoryList = listOf(
        "GENERAL",
        "BUSINESS",
        "entertainment",
        "health",
        "science",
        "sports",
        "technology"
    )
    Row(modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()).padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if(isSearchSelected){
            Log.e("ddd","SEARHC KEEP CALLING")
            OutlinedTextField(modifier = Modifier.fillMaxWidth(0.6f).height(50.dp)
                .border(1.dp, color = Color.Gray, shape = CircleShape)
                .clip(CircleShape), value = searchString, onValueChange = {
                searchString = it
            },
                trailingIcon = {
                    IconButton(onClick = {
                        isSearchSelected = false
                        dataModel.fetchNewsTopHeadLines(searchString)
                    }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                    }
                })
        }else{
            IconButton(onClick = {
                isSearchSelected = true
            }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            }
        }
        categoryList.forEach { category ->
            Button(modifier = Modifier.padding(5.dp), onClick = {
                dataModel.getNewsByCategory(category)
            }) {
                Text(text = category)
            }
        }

    }
}
