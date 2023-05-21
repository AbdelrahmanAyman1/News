package com.abdo.news.ui.fragment.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.abdo.news.R
import com.abdo.news.databinding.FragmentCategorisBinding

class CategoriesFragment : Fragment() {
    lateinit var fragmentCategoriesBinding: FragmentCategorisBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentCategoriesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_categoris, container, false)
        return fragmentCategoriesBinding.root
    }

    val categories = listOf(
        Category(
            "sports", R.drawable.sports_logo,
            R.string.sports, R.color.sportsBG
        ),

        Category(
            "general", R.drawable.newspaper,
            R.string.general, R.color.generalBG
        ),

        Category(
            "health", R.drawable.health_logo,
            R.string.health, R.color.healthBG
        ),

        Category(
            "business", R.drawable.bussines_logo,
            R.string.bussines, R.color.businessBG
        ),

        Category(
            "entertainment", R.drawable.cinema,
            R.string.entertainment, R.color.entertainmentBG
        ),

        Category(
            "science", R.drawable.science_logo,
            R.string.science, R.color.scienceBG
        )
    )

    val adapter = CategoriesAdapter(categories)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerVew()
    }

    private fun initRecyclerVew() {

        fragmentCategoriesBinding.categoriesRecycler.adapter = adapter
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