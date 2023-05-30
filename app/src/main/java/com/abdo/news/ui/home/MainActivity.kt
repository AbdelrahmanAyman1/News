package com.abdo.news.ui.home

import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.abdo.news.R
import com.abdo.news.ui.fragment.categories.CategoriesFragment
import com.abdo.news.ui.fragment.categories.Category
import com.abdo.news.ui.fragment.news.NewsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val categoriesFragment = CategoriesFragment()
    lateinit var drawerLayout: DrawerLayout
    lateinit var drawerIcon: ImageButton
    lateinit var categories: LinearLayout
    lateinit var name: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawer_layout)
        drawerIcon = findViewById(R.id.menu_ic)
        categories = findViewById(R.id.categories_linear)
        name = findViewById(R.id.category_name)
        pushFragment(categoriesFragment)

        categoriesFragment.onCategoryClickListener =
            object : CategoriesFragment.OnCategoryClickListener {
                override fun onCategoryClick(category: Category) {
                    pushFragment(NewsFragment.getInstance(category), true)
                }
            }
        drawerIcon.setOnClickListener {
            drawerLayout.open()
        }
        categories.setOnClickListener {
            pushFragment(categoriesFragment)
            name.text = "News App"

        }
    }

    fun pushFragment(fragment: Fragment, addToBackStack: Boolean = false) {

        val fragmentTransaction = supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
        if (addToBackStack) {
            fragmentTransaction.addToBackStack("")
        }
        fragmentTransaction.commit()
        drawerLayout.close()

    }
}