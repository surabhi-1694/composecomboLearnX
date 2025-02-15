package com.example.wheathercompose.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BaseURL = "https://newsapi.org"

    val client = OkHttpClient.Builder().addInterceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("User-Agent", "Android")
            .build()
        chain.proceed(request)
    }.build()


    private fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BaseURL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    val newsAPI:ApiInterface = getInstance().create(ApiInterface::class.java)

}