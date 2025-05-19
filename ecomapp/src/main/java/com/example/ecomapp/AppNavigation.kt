package com.example.ecomapp

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.ecomapp.Home.CategoryPageView
import com.example.ecomapp.Home.HomeScreen
import com.example.ecomapp.signup.SignUpScreen
import com.google.firebase.Firebase
import com.google.firebase.app
import com.google.firebase.auth.auth

@Composable
fun AppNavigation(modifier: Modifier) {
    val navController = rememberNavController()
    //can use nav controller  globally
      GlobalNavigator.navController = navController

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

        composable<CategoryPageRoute> { catpage->
            val catPageFlow = catpage.toRoute<CategoryPageRoute>()
            CategoryPageView(categoryId =  catPageFlow.categoryId)
        }
    }

}

object GlobalNavigator{
    lateinit var  navController:NavHostController
}
