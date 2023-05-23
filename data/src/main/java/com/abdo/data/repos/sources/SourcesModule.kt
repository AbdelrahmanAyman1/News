package com.abdo.data.repos.sources

import com.abdo.data.api.Services
import com.abdo.domain.repos.SourcesOnlineDateSource
import com.abdo.domain.repos.SourcesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object SourcesModule {

    @Provides
    fun provideOnlineDataSource(webServices: Services): SourcesOnlineDateSource {
        return SourcesOnlineDataSourceImpl(webServices)
    }

    @Provides
    fun provideSourcesRepo(onlineDateSource: SourcesOnlineDateSource): SourcesRepository {
        return SourcesRepositoryImpl(onlineDateSource)
    }

}