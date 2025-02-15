package com.example.bottomnavigationbar

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight

@Composable
fun SettingScreen(){
    val animatedColor by animateColorAsState(
        if (false) Color.Gray else Color.Red,
        label = "color"
    )
    Column(modifier = Modifier.fillMaxSize()
        .background(Color(0xFFD76EC0)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(modifier = Modifier.drawBehind {
            drawRect(animatedColor)
        }.graphicsLayer(rotationX = 30f,
            rotationY = 45f, rotationZ = 50f, scaleY = 5f, scaleX = 5f),
            text = "Settings", fontWeight = FontWeight.Bold)
    }
}