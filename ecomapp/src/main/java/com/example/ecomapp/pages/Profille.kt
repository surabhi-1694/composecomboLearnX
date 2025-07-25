package com.example.ecomapp.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ecomapp.AuthRoute
import com.example.ecomapp.GlobalNavigator
import com.example.ecomapp.HomeRoute
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


@Composable
fun Profile(modifier: Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(text= "Profile page")
        Button(onClick = {
            Firebase.auth.signOut()
            GlobalNavigator.navController.navigate(AuthRoute){ // name of screen where you want to navigate
                popUpTo<HomeRoute>{ // name of the screen which you wan to remove from back stack ; probably the previous one
                    inclusive = true
                }
            }
        }) {
            Text(text =" Logout")
        }

    }
}