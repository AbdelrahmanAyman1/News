package com.task1.news.di.newsFragmentModule

import com.abdo.news.ui.fragment.news.NewsDetailsFragment
import com.abdo.news.ui.fragment.news.NewsFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class NewsModule {

    @Provides
    fun provideNewsDetailsFragment(): NewsDetailsFragment {

        return NewsDetailsFragment()
    }

    @Provides
    fun provideNewsFragment(): NewsFragment {

        return NewsFragment()
    }
}