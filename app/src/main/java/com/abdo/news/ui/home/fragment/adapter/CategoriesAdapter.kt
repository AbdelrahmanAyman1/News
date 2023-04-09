package com.abdo.news.ui.home.fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abdo.news.R
import com.abdo.news.ui.home.fragment.Category
import com.google.android.material.card.MaterialCardView

class CategoriesAdapter(val categories: List<Category>) :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                if (viewType == isLeftSide) R.layout.item_categroy_left_side
                else R.layout.item_categroy_right_side, parent, false
            )
        return ViewHolder(view)
    }

    val isRightSide = 10
    val isLeftSide = 20
    override fun getItemViewType(position: Int): Int {
        if (position % 2 == 0) return isLeftSide
        return isRightSide
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val category = categories[position]
        holder.title.setText(category.titleId)
        holder.category.setImageResource(category.imageId)
        holder.parent.setCardBackgroundColor(holder.parent.context.getColor(category.backgroundColor))

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
        val parent: MaterialCardView = itemView.findViewById(R.id.parent)
        val title: TextView = itemView.findViewById(R.id.title_of_category)
        val category: ImageView = itemView.findViewById(R.id.categories_logo)

    }
}