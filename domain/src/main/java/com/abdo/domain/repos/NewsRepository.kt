package com.abdo.domain.repos

import com.abdo.domain.model.ArticlesItemDTO


interface NewsRepository {
    suspend fun getNews(sourceId: String): List<ArticlesItemDTO?>?
}

interface NewsOnlineDataSource {

    suspend fun getNewsBySourceId(sourceId: String): List<ArticlesItemDTO?>?
}