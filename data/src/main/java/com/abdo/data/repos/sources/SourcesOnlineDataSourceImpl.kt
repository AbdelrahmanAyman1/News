package com.abdo.data.repos.sources

import com.abdo.data.api.Services
import com.abdo.data.model.convertTo
import com.abdo.domain.model.SourcesItemDTO
import com.abdo.domain.model.SourcesResponseDTO
import com.abdo.domain.repos.SourcesOnlineDateSource
import javax.inject.Inject


class SourcesOnlineDataSourceImpl @Inject constructor(val webServices: Services) :
    SourcesOnlineDateSource {
    val apiKey = "6b69826cf6ac40af859d3c85db05ca02"

    override suspend fun getSources(category: String): List<SourcesItemDTO?>? {
        try {
            val result = webServices.getNewsSources(apiKey, category)
            return result.convertTo(SourcesResponseDTO::class.java).sources
        } catch (ex: Exception) {
            throw ex
        }
    }
}