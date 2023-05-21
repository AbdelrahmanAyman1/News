package com.abdo.news.repos.news

import com.abdo.news.api.Services
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