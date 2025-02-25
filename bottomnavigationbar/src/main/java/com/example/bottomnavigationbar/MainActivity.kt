package com.example.bottomnavigationbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.bottomnavigationbar.ui.theme.BottomNavigationTheme

// login screen -> navigate to bottomnav -> webview
//https://medium.com/@dimasoktanugraha47/mastering-android-jetpack-compose-textfield-and-validation-3abd17c75952
class MainActivity : ComponentActivity() {
    val dataModel:newsDataModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            BottomNavigationTheme {
                MainScreen(dataModel = dataModel, modifier = Modifier)
            }
        }
    }
}





