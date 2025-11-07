package com.example.bottomnavigationbar

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.ui.Modifier
import com.example.bottomnavigationbar.ui.theme.BottomNavigationTheme

// login screen -> navigate to bottomnav -> webview
//https://medium.com/@dimasoktanugraha47/mastering-android-jetpack-compose-textfield-and-validation-3abd17c75952
class MainActivity : ComponentActivity() {
    val dataModel: newsDataModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Log.e("Lifecycle_ ", "onCreate")

            BottomNavigationTheme {
                MainScreen(dataModel = dataModel, modifier = Modifier)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.e("Lifecycle_ ", "onStart")

    }

    override fun onResume() {
        super.onResume()
        Log.e("Lifecycle_ ", "onResume")

    }

    override fun onPause() {
        super.onPause()
        Log.e("Lifecycle_ ", "onPause")

    }

    override fun onStop() {
        super.onStop()
        Log.e("Lifecycle_ ", "onStop")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("Lifecycle_ ", "onDestroy")

    }
}





