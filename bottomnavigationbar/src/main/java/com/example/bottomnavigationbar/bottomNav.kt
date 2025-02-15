package com.example.bottomnavigationbar

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun BottomNav(){
    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar ={NavigationBar {
            NavigationBarItem(selected= true,
                modifier = Modifier.fillMaxSize(), onClick = {},
                icon = {
                    Icon(imageVector = Icons.Default.AccountBox, contentDescription = "AccountBox") }, label = { Text(text = "") })
        }}) { innerPadding->
            Text(text = "fffd ", modifier = Modifier.padding(innerPadding))
        }
}