package com.example.wheathercompose.network

sealed class NetworkResponse<out T> {
    data class Success<out T>(val data: T):NetworkResponse<T>()
    data class Error(val message:String):NetworkResponse<Nothing>()
     class Loading:NetworkResponse<Nothing>()
}
