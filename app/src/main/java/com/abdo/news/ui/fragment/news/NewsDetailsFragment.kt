package com.abdo.news.ui.fragment.news

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.abdo.news.R
import com.abdo.news.databinding.FragmentNewsDetailsBinding
import com.abdo.news.ui.fragment.news.NewsFragment.Companion.items
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailsFragment : Fragment() {

    lateinit var fragmentNewsDetailsBinding: FragmentNewsDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentNewsDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_news_details, container, false)

        return fragmentNewsDetailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        showFullArticle()
    }

    private fun showFullArticle() {
        var url = items?.url

        fragmentNewsDetailsBinding.newsDetailsFullArticle.setOnClickListener {

            var intent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context?.startActivity(intent)
        }
    }

    private fun initViews() {
        Glide.with(requireContext())
            .load(items?.urlToImage)
            .into(fragmentNewsDetailsBinding.newsDetailsImage)
        fragmentNewsDetailsBinding.newsDetailsSourceName.text = items?.source?.name
        fragmentNewsDetailsBinding.newsDetailsTitle.text = items?.title
        fragmentNewsDetailsBinding.newsDetailsTime.text = items?.publishedAt
        fragmentNewsDetailsBinding.newsDetailsDescription.text = items?.description
    }
}