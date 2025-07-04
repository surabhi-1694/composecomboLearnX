package com.example.wheathercompose

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.allowHardware
import coil3.request.crossfade
import com.example.wheathercompose.network.Article
import com.example.wheathercompose.network.NetworkResponse
import com.example.wheathercompose.ui.theme.CircularProgressBar
import com.example.wheathercompose.utils.ConvertDateFormate
import com.example.wheathercompose.utils.inputDateFormat
import com.example.wheathercompose.utils.outputDateFormat

@Composable
fun TopNewsPage(newsModel: NewsModel) {
//    val newslist = ArrayList<Article>()
//    val newslist = remember { mutableStateListOf<Article>() }

    /*
    * Live data
    * */
    val topNewsResultLiveData = newsModel.topNewsResultLiveData.observeAsState()
    val topNewsArticlesLiveData by newsModel.topNewsArticlesLiveData.observeAsState()

    /*
    * Stateflow
    *
    * */
    val topNewsResultStateFlowData by newsModel.topNewsResultStateFlow.collectAsStateWithLifecycle()
    val topNewsArticleStateFlowData by newsModel.topNewsArticlesStateFlow.collectAsStateWithLifecycle()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(6.dp)) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Button(onClick = {
                newsModel.getData()
                newsModel.getNewsFromFlow()
            }, modifier = Modifier.fillMaxWidth(0.6f))
            { Text(text = "Get Top News") }
        }
        /**
         * below code is observing api data and paa to UI
         * */
//        when (val result = topNewsResultLiveData.value) {
        when (val result = topNewsResultStateFlowData) {
            is NetworkResponse.Success -> {
//                newslist.addAll(result.data.articles)
//                ArticleList(topNewsArticlesLiveData)
                ArticleList(topNewsArticleStateFlowData)
                Log.e("newslist_Livedata", topNewsArticlesLiveData?.size.toString())
                Log.e("newslist_StateFlow", topNewsArticleStateFlowData?.size.toString())

            }

            is NetworkResponse.Error -> {
            }

            is NetworkResponse.Loading -> {
                /**
                 * if you want to display progressbar only at
                 * bottom of button with maxwidth pass Modifier.fillMaxWidth()
                 *
                 * if you want to display progressbar at center of entire screen Modifier.fillmaxsize()
                 * pass any customize modifier as you like
                 * */
                CircularProgressBar(Modifier.fillMaxWidth(), Color.Magenta, 30)
            }

            null -> ""
        }
    }

}

@Composable
fun ArticleList(newslist: List<Article>?) {
    LazyColumn(content = {
        itemsIndexed(newslist as ArrayList) { index: Int, item: Article ->
            TopNewsItem(index, item)
        }
    })

}


//Composable
/*
*
*
* val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
* */
@Composable
fun TopNewsItem(index: Int, item: Article) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)) {
        //articles
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(128.dp)
                    .clip(CircleShape)
                    .background(Color.Red)
                    .padding(0.dp),
                contentScale = ContentScale.Crop,
//                model = item.urlToImage,
                model = ImageRequest.Builder(
                    LocalContext.current
                ).data(item.urlToImage)
                    .crossfade(true).build(),
                contentDescription = "Article image"
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(text = item.title, fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = item.author,
                textAlign = TextAlign.Start,
                fontSize = 15.sp,
                modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(5.dp))
//                .apply(block = fun ImageRequest.Builder.() {
//                    crossfade(true)
//                    allowHardware(false)
//                })
            Image(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(5)),
                contentScale = ContentScale.Crop,
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(item.urlToImage)
                        .size(64)
                        .build()
                ), contentDescription = "image "
            )
        }

        Spacer(modifier = Modifier.height(5.dp))
        Text(text = item.description, maxLines = 5, fontSize = 12.sp, color = Color.Red)
        Spacer(modifier = Modifier.height(10.dp))
        Log.e("DATE_ ", "${item.publishedAt}")
        Text(text = ConvertDateFormate(inputFormat = inputDateFormat, outputFormate = outputDateFormat, stringDate = item.publishedAt), textAlign = TextAlign.Start)
        Spacer(modifier = Modifier.height(10.dp))
        Divider(color = Color.Blue, thickness = 1.5.dp)

    }

}
