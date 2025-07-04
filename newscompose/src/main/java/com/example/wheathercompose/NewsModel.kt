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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsModel: ViewModel() {
    private val newsApi = RetrofitInstance.newsAPI
/*
* mutable live data
* */

    private var _topNewsResult = MutableLiveData<NetworkResponse<TopNews>>()
    private var _topNewsArticles = MutableLiveData<List<Article>>()

    val topNewsResultLiveData:LiveData<NetworkResponse<TopNews>> = _topNewsResult
    val topNewsArticlesLiveData:LiveData<List<Article>> = _topNewsArticles

     fun getData() {
         _topNewsResult.value = NetworkResponse.Loading
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


 /* mutable live data
    * */

    /*
    * state flow
    * compulsory need initial value
    * */
    private var _topNewsResultMutableStateFlow = MutableStateFlow<NetworkResponse<TopNews?>>(NetworkResponse.Loading)
    var topNewsResultStateFlow :StateFlow<NetworkResponse<TopNews?>> = _topNewsResultMutableStateFlow

    private var _topNewsArticlesMutableStateFlow = MutableStateFlow<List<Article>>(emptyList())
     var topNewsArticlesStateFlow :StateFlow<List<Article>> = _topNewsArticlesMutableStateFlow


    fun getNewsFromFlow(){
        viewModelScope.launch {
            try {
                val response:Response<TopNews> =

                newsApi.getNewsData(sources = "techcrunch", apikey = "a6f7a32fb237403891836dd1dfa3cca1")

                if (response.isSuccessful) {
                    Log.e("response", "_Success ${response.body()}")
                    response.body()?.let { topnews ->
                        _topNewsResultMutableStateFlow.value = NetworkResponse.Success(topnews)
                        _topNewsArticlesMutableStateFlow.value = topnews.articles
                    }
                } else {
                    Log.e("response", "_Failure ${response.message()}")
                    _topNewsResultMutableStateFlow.value = NetworkResponse.Error(response.message())
                }
            }catch (e:Exception){
                e.printStackTrace()
                _topNewsResultMutableStateFlow.value = NetworkResponse.Error("Fail")

            }

        }
    }
}
