package com.abdo.news.api

import com.abdo.news.api.model.NewsResponse
import com.abdo.news.api.model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {
    @GET("v2/top-headlines/sources")
    fun getNewsSources(
        @Query("apiKey") apiKey: String,
        @Query("category") category: String
    ): Call<SourcesResponse>

    @GET("v2/everything")
    fun getNews(
        @Query("apikey") apiKey: String,
        @Query("sources") sources: String
    ): Call<NewsResponse>
}