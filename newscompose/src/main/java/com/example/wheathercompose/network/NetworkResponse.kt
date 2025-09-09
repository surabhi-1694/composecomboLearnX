package com.example.wheathercompose.network
// understanding of type T , out, in , return type how to create and call these function
//https://betulnecanli.medium.com/kotlin-generics-in-out-where-terms-with-examples-445dc0bb45d6

sealed class NetworkResponse<out T> {
    data class Success<out T>(val data: T):NetworkResponse<T>()
    data class Error(val message:String):NetworkResponse<Nothing>()
     object Loading:NetworkResponse<Nothing>()
}

