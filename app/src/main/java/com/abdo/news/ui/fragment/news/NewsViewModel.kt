package com.abdo.news.ui.fragment.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdo.domain.model.ArticlesItemDTO
import com.abdo.domain.model.SourcesItemDTO
import com.abdo.domain.repos.NewsRepository
import com.abdo.domain.repos.SourcesRepository
import com.abdo.news.ui.fragment.categories.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    val newsRepository: NewsRepository,
    val sourcesRepository: SourcesRepository
) : ViewModel() {
    val sourcesLiveData = MutableLiveData<List<SourcesItemDTO?>?>()
    val progressBarVisible = MutableLiveData<Boolean>(false)
    val newsLiveData = MutableLiveData<List<ArticlesItemDTO?>?>()
    val messageLiveData = MutableLiveData<String>()
    val searchedNewsList = MutableLiveData<List<ArticlesItemDTO?>?>()


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


    }

    fun loadNews(sourceClicked: SourcesItemDTO?, query: String?) {
        viewModelScope.launch {
            try {
                progressBarVisible.value = true
                val result = newsRepository.getNews(sourceClicked!!, query!!)
                newsLiveData.value = result
                progressBarVisible.value = false
            } catch (ex: Exception) {
                messageLiveData.value = ex.localizedMessage
                progressBarVisible.value = false
            }

        }

    }
}