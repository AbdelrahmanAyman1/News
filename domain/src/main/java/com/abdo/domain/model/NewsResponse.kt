package com.abdo.domain.model


data class NewsResponseDTO(


    val totalResults: Int? = null,


    val articles: List<ArticlesItemDTO?>? = null,


    ) : BaseResponseDTO()


data class ArticlesItemDTO(


    val publishedAt: String? = null,


    val author: String? = null,


    val urlToImage: String? = null,


    val description: String? = null,


    val source: SourcesItemDTO? = null,


    val title: String? = null,


    val url: String? = null,


    val content: String? = null
)
