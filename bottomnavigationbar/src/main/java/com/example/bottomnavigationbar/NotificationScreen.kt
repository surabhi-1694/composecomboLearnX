package com.example.bottomnavigationbar

import androidx.compose.animation.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NotificationScreen(){
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
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

        // Second List - Takes Half Screen Height
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
                    colors = CardDefaults.cardColors(containerColor = Color.Cyan)
                ) {
                    Text(
                        text = "List 2 - Item $index",
                        modifier = Modifier.padding(16.dp),
                        fontSize = 18.sp
                    )
                }
            }
        }
    }

}