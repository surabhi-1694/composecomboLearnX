package com.example.android2o

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.android2o.route.Routes.welcomeComposeScreen
import com.example.android2o.route.Routes.loginScreen_next
import com.example.android2o.route.Routes.loginScreen_next_key
import com.example.android2o.uipackage.LoginScreen
import com.example.android2o.uipackage.LoginScreenWithNavigation
import com.example.android2o.uipackage.NavigationDataReceiver

class WelcomeComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // setup navigation with compose to noncompose
            // compose nav without passing navcontroller
            // compose with use navigate via callback as if we call navigate in composable evrytime it call navigate when ui recompose
            // navigate without navigation graph with resultactivitylaunch
            WelcomeComposeNav()
        }
    }

    @Composable
     fun WelcomeComposeNav() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = welcomeComposeScreen, builder = {
            composable(welcomeComposeScreen) {
                //login function
                LoginScreenWithNavigation(navController)
            }
            composable (loginScreen_next){
                Toast.makeText(this@WelcomeComposeActivity,
                    "Landed to next screen ", Toast.LENGTH_LONG).show()
                //login function
                LoginScreen()
            }
            // loginScreen_next_key : This is the key value of data to be pass between two activity.
            composable(loginScreen_next+"/{$loginScreen_next_key}") {
                val name = it.arguments?.getString(loginScreen_next_key)
                name?.let { it1 -> NavigationDataReceiver(it1) }
            }

        })
    }
}
