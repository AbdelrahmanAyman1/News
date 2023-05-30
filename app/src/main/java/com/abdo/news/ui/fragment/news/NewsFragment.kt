package com.abdo.news.ui.fragment.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.abdo.domain.model.ArticlesItemDTO
import com.abdo.domain.model.SourcesItemDTO
import com.abdo.news.R
import com.abdo.news.databinding.FragmentNewsBinding
import com.abdo.news.ui.fragment.categories.Category
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment : Fragment() {

    companion object {

        var items: ArticlesItemDTO? = null
        var tagg: SourcesItemDTO? = null
        fun getInstance(category: Category): NewsFragment {
            val fragment = NewsFragment()
            fragment.category = category
            return fragment
        }
    }

    val newsViewModel: NewsViewModel by viewModels()

    @Inject
    lateinit var newsDetailsFragment: NewsDetailsFragment

    @Inject
    lateinit var adapter: NewsAdapter
    lateinit var category: Category
    lateinit var viewDataBinding: FragmentNewsBinding
    lateinit var search: SearchView
    lateinit var name: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        return viewDataBinding.root
    }

    override fun onStop() {
        super.onStop()
        search.isVisible = false
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        subscribeToLiveData()
        newsViewModel.getNewsSources(category)
        openNewsDetails()
        controlSearchView()
        search.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                newsViewModel.loadNews(tagg, query!!.lowercase())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newsViewModel.loadNews(tagg, newText!!.lowercase())
                return true
            }
        })
    }

    fun openNewsDetails() {
        adapter.onItemClick = object : NewsAdapter.OnItemClickListener {

            override fun onClick(position: Int, item: ArticlesItemDTO) {

                items = item
                var fragmentManager: FragmentManager = activity?.supportFragmentManager!!
                var fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.fragment_container, newsDetailsFragment)
                fragmentTransaction.addToBackStack("d")
                fragmentTransaction.commit()

            }


        }

    }

    private fun subscribeToLiveData() {
        newsViewModel.progressBarVisible.observe(viewLifecycleOwner, Observer { isVisible ->

            viewDataBinding.progress.isVisible = isVisible

        })
        newsViewModel.sourcesLiveData.observe(viewLifecycleOwner, Observer { data ->
            showTabs(data)
        })
        newsViewModel.newsLiveData.observe(viewLifecycleOwner, Observer {
            adapter.changeData(it)
        })
//
    }


    private fun initView() {
//
        viewDataBinding.recyclerViewNews.adapter = adapter
        search = requireActivity().findViewById(R.id.abd)
        name = requireActivity().findViewById(R.id.category_name)
        name.text = category.id
    }

    private fun showTabs(sources: List<SourcesItemDTO?>?) {
        sources?.forEach { item ->
            val tab = viewDataBinding.tabLayout.newTab()
            tab.tag = item
            tab.text = item?.name + ""
            viewDataBinding.tabLayout.addTab(tab)
        }
        viewDataBinding.tabLayout.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val source = tab?.tag as SourcesItemDTO
                    newsViewModel.loadNews(source, "")
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    val source = tab?.tag as SourcesItemDTO
                    tagg = source
                    newsViewModel.loadNews(source, "")
                    //  loadNews(source)
                }
            }
        )
        viewDataBinding.tabLayout.getTabAt(0)?.select()
    }

    fun controlSearchView() {

        search.isVisible = true

        search.setOnSearchClickListener {
            name.text = null
        }

        search.setOnCloseListener(SearchView.OnCloseListener {

            name.text = category.id
            newsViewModel.loadNews(tagg, null)

            return@OnCloseListener false
        })

    }


}