package com.example.wheathercompose.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

//
    //    https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=a6f7a32fb237403891836dd1dfa3cca1
    @GET("/v2/top-headlines")
    suspend fun getNewsData(
        @Query("sources") sources:String,
        @Query("apiKey") apikey:String
    ):Response<TopNews>


    @GET("https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=a6f7a32fb237403891836dd1dfa3cca1")
    suspend fun getNewsDataD():Response<TopNews>
}