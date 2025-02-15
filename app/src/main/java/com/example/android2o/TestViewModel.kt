package com.example.android2o

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class TestViewModel : ViewModel() {
    fun abcd(){
        viewModelScope.launch {

        }

    }
}