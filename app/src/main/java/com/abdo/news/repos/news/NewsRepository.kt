package com.abdo.news.repos.news

import com.abdo.news.api.model.ArticlesItem

interface NewsRepository {
    suspend fun getNews(sourceId: String): List<ArticlesItem?>?
}

interface NewsOnlineDataSource {

    suspend fun getNewsBySourceId(sourceId: String): List<ArticlesItem?>?
}