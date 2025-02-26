package com.example.bottomnavigationbar

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.kwabenaberko.newsapilib.models.Article
import com.kwabenaberko.newsapilib.models.Source

@SuppressLint("SuspiciousIndentation")
@Composable
fun HomeScreen(navController: NavController, dataModel: newsDataModel) {
    val articleSources by dataModel.articlesSource.observeAsState(emptyList())

    val articlevatSources by dataModel.categoryWiseNews.observeAsState(emptyList())

    val everyArticleSources by dataModel.everyArticlesSource.observeAsState(emptyList())

    val modifier = Modifier
    /**
    * design contains:
     * three list one with
     * 1. horizontal category list with search button
     * 2. below category with selected category news list
     * 3. below sources list we have list which will load by search keyword
     *
    * */
    Column(modifier = modifier.fillMaxSize()) {
        Text(
            modifier = modifier.padding(5.dp), text = "News App ",
            fontWeight = FontWeight.Bold, fontSize = 25.sp
        )
        //horizontal static list of category
        categoryList(dataModel,modifier)
        Box(
            modifier = modifier.fillMaxHeight().weight(1f)
        ) {
            Sourceslist(articlevatSources, navController,modifier)
        }
        Spacer(modifier = Modifier.height(5.dp))
        Divider(color = Color.Gray, thickness = 2.dp)
        Spacer(modifier = Modifier.height(5.dp))
        Box(
            modifier = modifier.fillMaxHeight().weight(1f)
        ) {
            Everyarticlelist(everyArticleSources)
        }
    }
}


@Composable
fun Everyarticlelist(
    everyArticleSources: List<Article>,
) {
        if (everyArticleSources.size > 0) {
            LazyColumn(modifier = Modifier.fillMaxSize().background(Color.Transparent)) {
                itemsIndexed(everyArticleSources) { index: Int, item: Article ->
                    Log.e("TAG ", item.title)
                    Log.e("TAG ", item.url)
                    Log.e("TAG ", item.description)

                    Card(
                        modifier = Modifier.padding(5.dp),
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onTertiary),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 10.dp,
                            hoveredElevation = 20.dp
                        )
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Spacer(modifier = Modifier.height(0.dp))
                            AsyncImage(
                                modifier = Modifier
                                    .size(120.dp)
                                    .padding(5.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop,
                                model = item.urlToImage,
                                contentDescription = "Article Image"
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Column(modifier = Modifier.padding(10.dp)) {
                                if (item.author != null) {
                                    Text(
                                        text = item.author,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 25.sp,
                                        color = Color.Black
                                    )
                                } else {
                                    Log.e("NEWSAPI", "AUTHOR NULL")
                                }
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(
                                    text = item.description,
                                    color = Color.Gray,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                                Spacer(modifier = Modifier.height(5.dp))

                            }
                        }
                    }

                }
            }
        } else {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "NO DATA FOUND",
                textAlign = TextAlign.Center
            )

        }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sourceslist(
    articleSources: List<Source>,
    navController: NavController,
    modifier: Modifier
) {

        if (articleSources.size > 0) {
            LazyColumn(modifier = modifier.fillMaxSize()) {
                itemsIndexed(articleSources) { index: Int, item: Source ->
                    Card(
                        modifier = modifier.padding(5.dp),
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onTertiary),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 10.dp,
                            hoveredElevation = 20.dp
                        ),
                        onClick = {
                            navController.navigate(
                                ScreenList(
                                    name = item.name,
                                    age = 0,
                                    address = item.description,
                                    url = item.url
                                )
                            )
                        }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(5.dp)
//                            .clickable {
//                                navController.navigate(
//                                    ScreenList(
//                                        name = item.name,
//                                        age = 0,
//                                        address = item.description
//                                    )
//                                )
//                            }
                        ) {
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = item.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 25.sp,
                                color = Color.Blue
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = item.url,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = Color.Blue
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = item.country,
                                color = Color.Blue,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                        }
                    }


                }

            }
        } else {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "NO DATA FOUND",
                textAlign = TextAlign.Center
            )

        }


}

@Composable
fun categoryList(dataModel: newsDataModel, modifier: Modifier) {

    var isSearchSelected by remember {
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
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (isSearchSelected) {
            Log.e("Search_", "SEARHC KEEP CALLING")
            OutlinedTextField(modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(50.dp)
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
        } else {
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
