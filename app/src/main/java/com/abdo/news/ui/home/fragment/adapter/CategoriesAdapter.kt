package com.abdo.news.ui.home.fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abdo.news.R
import com.abdo.news.ui.home.fragment.Category

class CategoriesAdapter(val categories: List<Category>) :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_categroy, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val category = categories[position]
        holder.title.setText(category.titleId)
        holder.category.setImageResource(category.imageId)
        holder.itemView.setBackgroundColor(category.backgroundColor)
        onItemClickListener.let {
            holder.itemView.setOnClickListener {
                onItemClickListener?.onItemClick(position, category)
            }
        }
    }

    var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(pos: Int, item: Category)
    }

    override fun getItemCount(): Int = categories.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title_of_category)
        val category: ImageView = itemView.findViewById(R.id.categories_logo)

    }
}