package com.abdo.news.repos.news

import com.abdo.news.api.model.ArticlesItem

class NewsRepositoryImpl(val newsOnlineDataSource: NewsOnlineDataSource) : NewsRepository {
    override suspend fun getNews(sourceId: String): List<ArticlesItem?>? {
        try {
            val result = newsOnlineDataSource.getNewsBySourceId(sourceId)
            return result
        } catch (ex: Exception) {
            throw ex
        }
    }
}