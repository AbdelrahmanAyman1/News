package com.abdo.data.repos.sources

import com.abdo.domain.model.SourcesItemDTO
import com.abdo.domain.repos.SourcesOnlineDateSource
import com.abdo.domain.repos.SourcesRepository
import javax.inject.Inject


class SourcesRepositoryImpl @Inject constructor(val onlineDateSource: SourcesOnlineDateSource) :
    SourcesRepository {
    override suspend fun getSources(category: String): List<SourcesItemDTO?>? {
        try {
            val result = onlineDateSource.getSources(category)
            return result
        } catch (ex: Exception) {
            throw ex
        }
    }
}