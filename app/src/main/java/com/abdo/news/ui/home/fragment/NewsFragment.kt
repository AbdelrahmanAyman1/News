package com.abdo.news.ui.home.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.abdo.news.R
import com.abdo.news.api.ApiManager
import com.abdo.news.api.model.NewsResponse
import com.abdo.news.api.model.SourcesItem
import com.abdo.news.api.model.SourcesResponse
import com.abdo.news.constants.Constants
import com.abdo.news.ui.home.fragment.adapter.NewsAdapter
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment() {

    companion object {
        fun getInstance(category: Category): NewsFragment {
            val fragment = NewsFragment()
            fragment.category = category
            return fragment
        }
    }

    lateinit var category: Category
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    lateinit var progressBar: ProgressBar
    lateinit var tabLayout: TabLayout
    lateinit var recyclerView: RecyclerView
    val adapter = NewsAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        getNewsSources()

    }

    private fun initView() {
        progressBar = requireView().findViewById(R.id.progress)
        tabLayout = requireView().findViewById(R.id.tabLayout)
        recyclerView = requireView().findViewById(R.id.recycler_view_news)
        recyclerView.adapter = adapter
    }

    private fun getNewsSources() {
        ApiManager.getApis()
            .getNewsSources(Constants.apiKey, category.id)
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    progressBar.isVisible = false
                    showTabs(response.body()?.sources)
                    Log.e("response", response.body().toString())
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    progressBar.isVisible = false

                }
            })
    }

    private fun showTabs(sources: List<SourcesItem?>?) {
        sources?.forEach { item ->
            val tab = tabLayout.newTab()
            tab.tag = item
            tab.text = item?.name
            tabLayout.addTab(tab)
        }
        tabLayout.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    // sources?.get(tab?.position?:0)
                    val source = tab?.tag as SourcesItem
                    loadNews(source)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    val source = tab?.tag as SourcesItem
                    loadNews(source)
                }
            }
        )
        tabLayout.getTabAt(0)?.select()
    }

    fun loadNews(source: SourcesItem) {
        adapter.changeData(null)
        progressBar.isVisible = true
        ApiManager.getApis()
            .getNews(Constants.apiKey, source.id ?: "")
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    progressBar.isVisible = false
                    adapter.changeData(response.body()?.articles)
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {

                    Toast.makeText(requireContext(), "Error Loading News", Toast.LENGTH_LONG).show()
                    progressBar.isVisible = false
                }
            })
    }

}