package com.abdo.news.repos.sources

import com.abdo.news.api.model.SourcesItem

class SourcesRepositoryImpl(val onlineDateSource: SourcesOnlineDateSource) : SourcesRepository {
    override suspend fun getSources(category: String): List<SourcesItem?>? {
        try {
            val result = onlineDateSource.getSources(category)
            return result
        } catch (ex: Exception) {
            throw ex
        }
    }
}