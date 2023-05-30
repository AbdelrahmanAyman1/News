package com.abdo.data.repos.sources.news

import com.abdo.data.api.Services
import com.abdo.data.model.convertTo

import com.abdo.domain.model.ArticlesItemDTO
import com.abdo.domain.model.NewsResponseDTO
import com.abdo.domain.model.SourcesItemDTO
import com.abdo.domain.repos.NewsOnlineDataSource
import javax.inject.Inject


class NewsOnlineDataSourceImpl @Inject constructor(val webServices: Services) :
    NewsOnlineDataSource {
    val apiKey = "6b69826cf6ac40af859d3c85db05ca02"

    override suspend fun getNewsBySourceId(
        source: SourcesItemDTO,
        query: String?
    ): List<ArticlesItemDTO?>? {
        try {
            val result = webServices.getNews(apiKey, source.id!!, query ?: "")
            return result.convertTo(NewsResponseDTO::class.java).articles
        } catch (ex: Exception) {
            throw ex
        }
    }
}