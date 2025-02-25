package com.example.bottomnavigationbar

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bottomnavigationbar.utils.constant
import com.kwabenaberko.newsapilib.NewsApiClient
import com.kwabenaberko.newsapilib.models.Article
import com.kwabenaberko.newsapilib.models.Source
import com.kwabenaberko.newsapilib.models.request.EverythingRequest
import com.kwabenaberko.newsapilib.models.request.SourcesRequest
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest
import com.kwabenaberko.newsapilib.models.response.ArticleResponse
import com.kwabenaberko.newsapilib.models.response.SourcesResponse


class newsDataModel : ViewModel() {

    private val _articlesSource = MutableLiveData<List<Source>>()
    private val _everyArticlesSource = MutableLiveData<List<Article>>()

    val _categoryWiseNews = MutableLiveData<List<Source>>()
    val categoryWiseNews:LiveData<List<Source>> = _categoryWiseNews

    val articlesSource: LiveData<List<Source>> = _articlesSource
    val everyArticlesSource: LiveData<List<Article>> = _everyArticlesSource
    val newsApiClient = NewsApiClient(constant.apikey)
    init {
        fetchNewsTopHeadLines()
        getNewsByCategory()
    }

    fun getNewsByCategory(category: String = "GENERAL"){
        val sourceRequest = SourcesRequest.Builder().language("en").category(category).build()
        newsApiClient.getSources(sourceRequest,object :NewsApiClient.SourcesCallback{
            override fun onSuccess(response: SourcesResponse?) {
                response?.sources?.let {
                    _categoryWiseNews.postValue(it)
                }
            }

            override fun onFailure(throwable: Throwable?) {
                Log.e("NEws API",throwable?.localizedMessage.toString())
            }

        })

    }
    fun fetchNewsTopHeadLines(searchString:String? = "") {
        val request = TopHeadlinesRequest.Builder().language("en").country("US").build()
        val everythingRequest = EverythingRequest.Builder().q(searchString).language("en").build()


        newsApiClient.getEverything(everythingRequest, object : NewsApiClient.ArticlesResponseCallback {
            override fun onSuccess(response: ArticleResponse?) {
                Log.i("NewsAPI everythingRequest1 ", response.toString())
                    response?.articles?.let {
                        _everyArticlesSource.postValue(it)
                }
                response?.articles?.forEach {
                    Log.i("NewsAPI SOurces forEach  ", "NewsAPI ${it.author}")
                }
            }

            override fun onFailure(throwable: Throwable?) {
                Log.i("NewsAPI everythingRequest ", throwable?.localizedMessage.toString())

            }

        })
    }


}