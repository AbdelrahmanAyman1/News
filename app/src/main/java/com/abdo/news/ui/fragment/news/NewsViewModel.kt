package com.abdo.news.ui.fragment.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdo.news.api.model.ArticlesItem
import com.abdo.news.api.model.SourcesItem
import com.abdo.news.repos.news.NewsRepository
import com.abdo.news.repos.sources.SourcesRepository
import com.abdo.news.ui.fragment.categories.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    val newsRepository: NewsRepository,
    val sourcesRepository: SourcesRepository
) : ViewModel() {
    val sourcesLiveData = MutableLiveData<List<SourcesItem?>?>()
    val progressBarVisible = MutableLiveData<Boolean>(false)
    val newsLiveData = MutableLiveData<List<ArticlesItem?>?>()
    val messageLiveData = MutableLiveData<String>()
    val searchedNewsList = MutableLiveData<List<ArticlesItem?>?>()


    fun getNewsSources(category: Category) {

        viewModelScope.launch {
            try {

                progressBarVisible.value = true
                val result = sourcesRepository.getSources(category.id)
                sourcesLiveData.value = result
                progressBarVisible.value = false
            } catch (ex: Exception) {
                messageLiveData.value = ex.localizedMessage
                progressBarVisible.value = false
            }
        }

//        ApiManager.getApis()
//            .getNewsSources(Constants.apiKey, category.id)
//            .enqueue(object : Callback<SourcesResponse> {
//                override fun onResponse(
//                    call: Call<SourcesResponse>,
//                    response: Response<SourcesResponse>
//                ) {
//                    progressBarVisible.value = false
//                    sourcesLiveData.value = response.body()?.sources
//                    Log.e("response", response.body().toString())
//                }
//
//                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
//                    progressBarVisible.value = false
//
//                }
//            })
    }

    fun loadNews(source: SourcesItem) {
        viewModelScope.launch {
            try {
                progressBarVisible.value = true
                val result = newsRepository.getNews(source.id!!)
                newsLiveData.value = result
                progressBarVisible.value = false
            } catch (ex: Exception) {
                messageLiveData.value = ex.localizedMessage
                progressBarVisible.value = false
            }

        }
//        ApiManager.getApis()
//            .getNews(Constants.apiKey, source.id ?: "")
//            .enqueue(object : Callback<NewsResponse> {
//                override fun onResponse(
//                    call: Call<NewsResponse>,
//                    response: Response<NewsResponse>
//                ) {
//                    progressBarVisible.value = false
//                    newsList.value = (response.body()?.articles)
//                }
//
//                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
//
//                    //Toast.makeText(requireContext(), "Error Loading News", Toast.LENGTH_LONG).show()
//                    progressBarVisible.value = false
//
//                }
//            })
    }
//    fun searchByKeywordAndResource(keyword:String,source: SourcesItem){
//
//        progressBarVisible.value=true
//        ApiManager.getApis().searchInNews(Constants.apiKey,keyword,source.id!!).enqueue(
//            object :Callback<NewsResponse>{
//                override fun onResponse(
//                    call: Call<NewsResponse>,
//                    response: Response<NewsResponse>
//                ) {
//                    progressBarVisible.value=false
//                    searchedNewsList.value=response.body()?.articles
//                }
//
//                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
//                    progressBarVisible.value=false
//
//                }
//            }
//        )
//
//    }

}