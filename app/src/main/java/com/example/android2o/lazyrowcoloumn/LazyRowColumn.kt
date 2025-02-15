package com.example.android2o.lazyrowcoloumn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class LazyRowColumn : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListDemo()
        }
    }
}
