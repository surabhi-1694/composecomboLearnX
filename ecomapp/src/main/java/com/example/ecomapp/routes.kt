package com.example.ecomapp

import kotlinx.serialization.Serializable

@Serializable
object AuthRoute

@Serializable
object LoginRoute

@Serializable
object SignUpRoute

@Serializable
object HomeRoute
//data class HomeRoute(val name:String,val email:String,val uid:String)

@Serializable
object ProfileRoute

@Serializable
data class CategoryPageRoute(val categoryId:String)




