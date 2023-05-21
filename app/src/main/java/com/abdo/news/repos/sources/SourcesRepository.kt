package com.abdo.news.repos.sources

import com.abdo.news.api.model.SourcesItem

interface SourcesRepository {
    suspend fun getSources(category: String): List<SourcesItem?>?
}

interface SourcesOnlineDateSource {
    suspend fun getSources(category: String): List<SourcesItem?>?
}