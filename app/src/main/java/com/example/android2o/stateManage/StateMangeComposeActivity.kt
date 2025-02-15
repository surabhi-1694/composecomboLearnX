package com.example.android2o.stateManage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

class StateMangeComposeActivity : ComponentActivity() {
    val viewModel:StateManageViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StateManageScreen(viewModel)
        }
    }
}
