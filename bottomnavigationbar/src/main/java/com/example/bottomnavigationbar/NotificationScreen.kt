package com.example.bottomnavigationbar

import androidx.compose.animation.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NotificationScreen(){
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
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

    var searchString by rememberSaveable {
        mutableStateOf("")
    }
    var filterList by rememberSaveable {
        mutableStateOf(categoryList)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        // First List - Takes Half Screen Height
        LazyColumn(
            modifier = Modifier
                .weight(1f) // Equal width for both lists
                .fillMaxHeight()
        ) {
            items(20) { index ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.LightGray)
                ) {
                    Text(
                        text = "List 1 - Item $index",
                        modifier = Modifier.padding(16.dp),
                        fontSize = 18.sp
                    )
                }
            }
        }

        OutlinedTextField(modifier = Modifier.fillMaxWidth().height(90.dp),
            value = searchString, onValueChange = {
                searchString = it
                if(it.isEmpty()){
                    filterList = categoryList
                }else if(it.length == 2){
                    filterList =   filterContactsSlidingWindow(contacts = categoryList,searchString)
                }
            }, trailingIcon = {
                IconButton(onClick = {
                    //verify
                    filterList =   filterContactsSlidingWindow(contacts = categoryList,searchString)
                }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "")
                }
            }
        )

        // Second List - Takes Half Screen Height
        LazyColumn(
            modifier = Modifier
                .weight(1f) // Equal width for both lists
                .fillMaxHeight()
        ) {
            items(filterList.size) { index ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Cyan)
                ) {
                    Text(
                        text = filterList[index],
                        modifier = Modifier.padding(16.dp),
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

fun filterContactsSlidingWindow(contacts: List<String>, query: String): List<String> {
    val lowerQuery = query.lowercase()
    val filtered = mutableListOf<String>()

    for (contact in contacts) {
        val lowerContact = contact.lowercase()
        if (slidingWindowMatch(lowerContact, lowerQuery)) {
            filtered.add(contact)
        }
    }

    return filtered
}

// DSA-based sliding window matcher
fun slidingWindowMatch(text: String, pattern: String): Boolean {
    val n = text.length
    val m = pattern.length

    if (m > n) return false

    for (i in 0..(n - m)) { val window = text.substring(i, i + m)
        if (window == pattern) return true
    }

    return false
}
