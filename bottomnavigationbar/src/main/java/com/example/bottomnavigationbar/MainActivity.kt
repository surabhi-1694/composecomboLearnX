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

class MainActivity : ComponentActivity() {
    val dataModel:newsDataModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            BottomNavigationTheme {
                NavHost(navController, startDestination = ScreenMain) {
                    composable<ScreenMain>{
                        MainScreen(navController = navController,dataModel= dataModel,modifier = Modifier)
                    }
                    composable<ScreenList> {
                        val args = it.toRoute<ScreenList>()
                        HomeListScreen(modifier = Modifier,args)
                    }
                }

            }
        }
    }
}





