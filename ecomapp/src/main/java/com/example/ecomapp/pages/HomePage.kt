package com.example.ecomapp.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ecomapp.Home.BannerView
import com.example.ecomapp.Home.Categoriesview
import com.example.ecomapp.Home.HeaderView
import com.example.ecomapp.signup.AuthViewModel

@Composable
fun HomePage(modifier: Modifier,
             authViewModel: AuthViewModel = viewModel()){
      Column (modifier = modifier.padding(10.dp)
        ) {
          HeaderView(modifier,authViewModel)
          //set banner  list here
          BannerView(modifier = Modifier.height(160.dp),authViewModel)
          //Category view
          Categoriesview(modifier = Modifier,authViewModel)

          /*
          *
          *
          * Brand	HP
Model Name	HP Laptop
Screen Size	15.6 Inches
Colour	Silver
Hard Disk Size	512 GB
CPU Model	Intel Core i7
RAM Memory Installed Size	16 GB

          * */

    }
}