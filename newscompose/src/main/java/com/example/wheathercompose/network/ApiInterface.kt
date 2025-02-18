package com.example.wheathercompose.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

//
    @GET("/v2/top-headlines")
    suspend fun getNewsData(
        @Query("sources") sources:String,
        @Query("apiKey") apikey:String
    ):Response<TopNews>


    @GET("https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=Your key")
    suspend fun getNewsDataD():Response<TopNews>
}