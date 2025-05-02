package com.example.ecomapp.Home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.ecomapp.AuthRoute
import com.example.ecomapp.HomeRoute
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun HomeScreen(modifier: Modifier, navController: NavHostController) {

    Column (modifier = modifier.fillMaxSize()){
        Text(text = "Welcome to Home Screen")
        Button(onClick = {
            Firebase.auth.signOut()
            navController.navigate(AuthRoute){
                popUpTo<HomeRoute>{
                    inclusive = true
                }
            }
        }) {
            Text(text = "Logout")
        }

    }
}