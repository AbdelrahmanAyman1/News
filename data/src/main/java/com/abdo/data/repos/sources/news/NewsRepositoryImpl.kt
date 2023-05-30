package com.abdo.data.repos.sources.news

import com.abdo.domain.model.ArticlesItemDTO
import com.abdo.domain.model.SourcesItemDTO
import com.abdo.domain.repos.NewsOnlineDataSource
import com.abdo.domain.repos.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(val newsOnlineDataSource: NewsOnlineDataSource) :
    NewsRepository {
    override suspend fun getNews(source: SourcesItemDTO, query: String?): List<ArticlesItemDTO?>? {
        try {
            val result = newsOnlineDataSource.getNewsBySourceId(source, query ?: "")
            return result
        } catch (ex: Exception) {
            throw ex
        }
    }
}