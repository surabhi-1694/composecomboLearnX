package com.example.ecomapp.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ecomapp.signup.AuthViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.launch


@Composable
fun HomePage(modifier: Modifier,
             authViewModel: AuthViewModel = viewModel()){

    var name by remember {
        mutableStateOf("")
    }
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    LaunchedEffect(Unit) { //why to use this to use coroutine scopes
        lifecycleScope.launch {
            name = authViewModel.getUserName().toString()
        }
    }


    Column (modifier = Modifier.fillMaxSize().fillMaxSize().padding(15.dp)) {

        Text(text = "Welcome Back")
        Text(text = name)




    }
}