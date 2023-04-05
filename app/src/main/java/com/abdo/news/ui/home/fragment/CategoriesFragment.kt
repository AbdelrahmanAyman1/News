package com.abdo.news.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.abdo.news.R
import com.abdo.news.ui.home.fragment.adapter.CategoriesAdapter

class CategoriesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_categoris, container, false)
    }

    val categories = listOf(
        Category(
            "", R.drawable.sports_logo,
            R.string.sports, R.color.sportsBG
        ),

        Category(
            "", R.drawable.newspaper,
            R.string.general, R.color.generalBG
        ),

        Category(
            "", R.drawable.health_logo,
            R.string.health, R.color.healthBG
        ),

        Category(
            "", R.drawable.bussines_logo,
            R.string.bussines, R.color.businessBG
        ),

        Category(
            "", R.drawable.cinema,
            R.string.entertainment, R.color.entertainmentBG
        ),

        Category(
            "", R.drawable.science_logo,
            R.string.science, R.color.scienceBG
        )
    )
    lateinit var recyclerView: RecyclerView
    val adapter = CategoriesAdapter(categories)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerVew()
    }

    private fun initRecyclerVew() {
        recyclerView = requireView().findViewById(R.id.categories_recycler)
        recyclerView.adapter = adapter
        adapter.onItemClickListener = object : CategoriesAdapter.OnItemClickListener {
            override fun onItemClick(pos: Int, item: Category) {
                onCategoryClickListener?.onCategoryClick(category = item)
            }
        }

    }

    var onCategoryClickListener: OnCategoryClickListener? = null

    interface OnCategoryClickListener {
        fun onCategoryClick(category: Category)
    }
}