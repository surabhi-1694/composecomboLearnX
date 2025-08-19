package com.example.ecomapp

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.ecomapp.Home.CategoryWiseProductListPageView
import com.example.ecomapp.Home.HomeScreen
import com.example.ecomapp.pages.CheckOutScreen
import com.example.ecomapp.pages.OrderListScreen
import com.example.ecomapp.signup.SignUpScreen
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firestore.admin.v1.Index.IndexField.Order

@Composable
fun AppNavigation(modifier: Modifier, context: MainActivity, _showDialog: MutableState<Boolean>) {
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
        composable<CheckoutRoute> {
            CheckOutScreen(modifier,paymentInterface = context,navController)
        }

        composable<OrderRoute> {
            OrderListScreen(modifier,navController)
        }

        composable<CategoryPageRoute> { catpage->
            val catPageFlow = catpage.toRoute<CategoryPageRoute>()
            CategoryWiseProductListPageView(categoryId =  catPageFlow.categoryId)
        }
    }

}

object GlobalNavigator{
    lateinit var  navController:NavHostController
}
