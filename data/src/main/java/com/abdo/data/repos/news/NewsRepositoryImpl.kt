package com.abdo.data.repos.news

import com.abdo.domain.model.ArticlesItemDTO
import com.abdo.domain.repos.NewsOnlineDataSource
import com.abdo.domain.repos.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(val newsOnlineDataSource: NewsOnlineDataSource) :
    NewsRepository {
    override suspend fun getNews(sourceId: String): List<ArticlesItemDTO?>? {
        try {
            val result = newsOnlineDataSource.getNewsBySourceId(sourceId)
            return result
        } catch (ex: Exception) {
            throw ex
        }
    }
}