package com.abdo.news.ui.fragment.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.abdo.news.R
import com.abdo.news.api.model.SourcesItem
import com.abdo.news.databinding.FragmentNewsBinding
import com.abdo.news.ui.fragment.categories.Category
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment : Fragment() {

    companion object {
        fun getInstance(category: Category): NewsFragment {
            val fragment = NewsFragment()
            fragment.category = category
            return fragment
        }
    }

    @Inject
    lateinit var adapter: NewsAdapter
    lateinit var category: Category
    lateinit var viewDataBinding: FragmentNewsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        //return inflater.inflate(R.layout.fragment_news, container, false)
        return viewDataBinding.root
    }


    lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        subscribeToLiveData()
        viewModel.getNewsSources(category)

    }

    private fun subscribeToLiveData() {
        viewModel.progressBarVisible.observe(viewLifecycleOwner, Observer { isVisible ->

            viewDataBinding.progress.isVisible = isVisible

        })
        viewModel.sourcesLiveData.observe(viewLifecycleOwner, Observer { data ->
            showTabs(data)
        })
        viewModel.newsLiveData.observe(viewLifecycleOwner, Observer {
            adapter.changeData(it)
        })
        viewModel.searchedNewsList.observe(viewLifecycleOwner, {

        })
    }


    private fun initView() {
//        progressBar = requireView().findViewById(R.id.progress)
//        tabLayout = requireView().findViewById(R.id.tabLayout)
//        recyclerView = requireView().findViewById(R.id.recycler_view_news)
        viewDataBinding.recyclerViewNews.adapter = adapter

    }

    private fun showTabs(sources: List<SourcesItem?>?) {
        sources?.forEach { item ->
            val tab = viewDataBinding.tabLayout.newTab()
            tab.tag = item
            tab.text = item?.name
            viewDataBinding.tabLayout.addTab(tab)
        }
        viewDataBinding.tabLayout.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    // sources?.get(tab?.position?:0)
                    val source = tab?.tag as SourcesItem
                    viewModel.loadNews(source)
                    //loadNews(source)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    val source = tab?.tag as SourcesItem
                    viewModel.loadNews(source)
                    //  loadNews(source)
                }
            }
        )
        viewDataBinding.tabLayout.getTabAt(0)?.select()
    }
//    fun controlSearchView() {
//
//        search.isVisible = true
//
//        search.setOnSearchClickListener {
//            name.text = null
//        }
//
//        search.setOnCloseListener(SearchView.OnCloseListener {
//
//            name.text = "News App"
//            newsViewModel.getNewsBySource(tagg, null)
//
//            return@OnCloseListener false
//        })
//
//    }


}