package com.example.wheathercompose

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wheathercompose.network.Article
import com.example.wheathercompose.network.NetworkResponse
import com.example.wheathercompose.network.RetrofitInstance
import com.example.wheathercompose.network.TopNews
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsModel: ViewModel() {
    private val newsApi = RetrofitInstance.newsAPI

    private var _topNewsResult = MutableLiveData<NetworkResponse<TopNews>>()
    private var _topNewsArticles = MutableLiveData<List<Article>>()

    val topNewsResultLiveData:LiveData<NetworkResponse<TopNews>> = _topNewsResult
    val topNewsArticlesLiveData:LiveData<List<Article>> = _topNewsArticles


     fun getData() {
         _topNewsResult.value = NetworkResponse.Loading()
         viewModelScope.launch {
             try {
                 val response:Response<TopNews> =
                     newsApi.getNewsData(sources = "techcrunch", apikey = "a6f7a32fb237403891836dd1dfa3cca1")
                 if (response.isSuccessful) {
                     Log.e("response", "_Success ${response.body()}")

                     response.body()?.let { topnews ->
                         _topNewsResult.value = NetworkResponse.Success(topnews)
                         _topNewsArticles.value = topnews.articles
                     }
                 } else {
                     Log.e("response", "_Failure ${response.message()}")
                     _topNewsResult.value = NetworkResponse.Error(response.message())
                 }
             }catch (e:Exception){
                 e.printStackTrace()
                 _topNewsResult.value = NetworkResponse.Error("Fail")

             }

         }

    }
}
