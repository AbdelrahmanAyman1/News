package com.abdo.news.repos.news

import com.abdo.news.api.Services
import com.abdo.news.api.model.ArticlesItem
import com.abdo.news.constants.Constants

class NewsOnlineDataSourceImpl(val webServices: Services) : NewsOnlineDataSource {

    override suspend fun getNewsBySourceId(sourceId: String): List<ArticlesItem?>? {
        try {
            val result = webServices.getNews(Constants.apiKey, sourceId)
            return result.articles
        } catch (ex: Exception) {
            throw ex
        }
    }
}