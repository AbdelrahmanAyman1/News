package com.abdo.news.ui.home.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.abdo.news.R
import com.abdo.news.api.ApiManager
import com.abdo.news.api.model.SourcesResponse
import com.abdo.news.constants.Constants
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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        getNewsSources()
    }

    private fun initView() {
        progressBar = requireView().findViewById(R.id.progress)
    }

    private fun getNewsSources() {
        ApiManager.getApis()
            .getNewsSources(Constants.apiKey)
            .enqueue(object : retrofit2.Callback<SourcesResponse> {

                override fun onResponse(
                    call: retrofit2.Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    progressBar.isVisible = false
                    Log.e("response", response.body().toString())
                }

                override fun onFailure(call: retrofit2.Call<SourcesResponse>, t: Throwable) {
                    progressBar.isVisible = false
                    TODO("Not yet implemented")
                }
            })
    }
}