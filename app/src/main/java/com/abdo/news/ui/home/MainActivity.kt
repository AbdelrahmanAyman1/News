package com.abdo.news.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.abdo.news.R
import com.abdo.news.ui.home.fragment.CategoriesFragment
import com.abdo.news.ui.home.fragment.Category
import com.abdo.news.ui.home.fragment.NewsFragment

class MainActivity : AppCompatActivity() {

    val categoriesFragment = CategoriesFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pushFragment(categoriesFragment, true)
        categoriesFragment.onCategoryClickListener =
            object : CategoriesFragment.OnCategoryClickListener {
                override fun onCategoryClick(category: Category) {
                    pushFragment(NewsFragment.getInstance(category), true)
                }
            }
    }

    fun pushFragment(fragment: Fragment, addToBackStack: Boolean = false) {

        val fragmentTransaction = supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
        if (addToBackStack) {
            fragmentTransaction.addToBackStack("")
            fragmentTransaction.commit()
        }
    }
}