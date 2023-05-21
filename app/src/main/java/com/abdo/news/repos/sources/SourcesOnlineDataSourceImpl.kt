package com.abdo.news.repos.sources

import com.abdo.news.api.Services
import com.abdo.news.api.model.SourcesItem
import com.abdo.news.constants.Constants

class SourcesOnlineDataSourceImpl(val webServices: Services) : SourcesOnlineDateSource {
    override suspend fun getSources(category: String): List<SourcesItem?>? {
        try {
            val result = webServices.getNewsSources(Constants.apiKey, category)
            return result.sources
        } catch (ex: Exception) {
            throw ex
        }
    }
}