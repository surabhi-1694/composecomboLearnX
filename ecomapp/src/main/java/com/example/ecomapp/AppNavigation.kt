package com.example.ecomapp

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecomapp.Home.HomeScreen
import com.example.ecomapp.signup.SignUpScreen
import com.google.firebase.Firebase
import com.google.firebase.app
import com.google.firebase.auth.auth

@Composable
fun AppNavigation(modifier: Modifier) {
    val navController = rememberNavController()

    val isLoggedIn = Firebase.auth.currentUser != null
    Log.e("name ","${Firebase.auth.currentUser}")
    val firstPage = if(isLoggedIn) HomeRoute else AuthRoute

    NavHost(navController, startDestination = firstPage) {
        //Start Destination
        composable<AuthRoute> {
            AuthScreen(modifier,navController)
        }
        composable<LoginRoute> {
            LoginScreen(modifier,navController)
        }
        composable<SignUpRoute> {
            SignUpScreen(modifier,navController)
        }
        composable<HomeRoute> {
            HomeScreen(modifier,navController)
        }
    }






}