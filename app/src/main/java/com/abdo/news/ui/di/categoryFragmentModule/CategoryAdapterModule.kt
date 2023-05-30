package com.task1.news.di.categoryFragmentModule

import com.abdo.news.ui.fragment.categories.CategoriesAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent


@Module
@InstallIn(FragmentComponent::class)
class CategoryAdapterModule {

    @Provides
    fun provideCategoryAdapter(): CategoriesAdapter {

        return CategoriesAdapter(null!!)
    }
}