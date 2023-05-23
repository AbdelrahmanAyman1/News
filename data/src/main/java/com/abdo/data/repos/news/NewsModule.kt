package com.abdo.data.repos.news

import com.abdo.data.api.Services
import com.abdo.domain.repos.NewsOnlineDataSource
import com.abdo.domain.repos.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NewsModule {

    @Provides
    fun provideNewsRepo(newsOnlineDataSource: NewsOnlineDataSource): NewsRepository {
        return NewsRepositoryImpl(newsOnlineDataSource)
    }

    @Provides
    fun provideNewsOnlineDataSource(webServices: Services): NewsOnlineDataSource {
        return NewsOnlineDataSourceImpl(webServices)
    }
}