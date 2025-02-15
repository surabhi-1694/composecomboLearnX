package com.example.wheathercompose.network

data class TopNews(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)