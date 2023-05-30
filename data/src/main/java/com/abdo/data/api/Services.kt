package com.abdo.data.api

import com.abdo.data.model.NewsResponse
import com.abdo.data.model.SourcesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {
    @GET("v2/top-headlines/sources")
    suspend fun getNewsSources(
        @Query("apiKey") apiKey: String,
        @Query("category") category: String
    ): SourcesResponse

    @GET("v2/everything")
    suspend fun getNews(
        @Query("apikey") apiKey: String,
        @Query("sources") sources: String,
        @Query("q") query: String
    ): NewsResponse

//    @GET("v2/everything")
//    fun searchInNews(
//        @Query("apikey") apiKey: String,
//        @Query("q") keyword: String,
//        @Query("sources") sources: String
//    ): Call<NewsResponse>
}