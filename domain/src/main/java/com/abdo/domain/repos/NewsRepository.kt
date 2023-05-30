package com.abdo.domain.repos

import com.abdo.domain.model.ArticlesItemDTO
import com.abdo.domain.model.SourcesItemDTO


interface NewsRepository {
    suspend fun getNews(source: SourcesItemDTO, query: String?): List<ArticlesItemDTO?>?
}

interface NewsOnlineDataSource {

    suspend fun getNewsBySourceId(source: SourcesItemDTO, query: String?): List<ArticlesItemDTO?>?
}