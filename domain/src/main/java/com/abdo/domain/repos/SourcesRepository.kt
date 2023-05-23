package com.abdo.domain.repos

import com.abdo.domain.model.SourcesItemDTO


interface SourcesRepository {
    suspend fun getSources(category: String): List<SourcesItemDTO?>?
}

interface SourcesOnlineDateSource {
    suspend fun getSources(category: String): List<SourcesItemDTO?>?
}