package com.example.ecomapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecomapp.signup.SignUpScreen

@Composable
fun AppNavigation(modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = AuthRoute) {
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
    }






}