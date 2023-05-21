package com.abdo.news.ui.fragment.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.abdo.news.R
import com.abdo.news.databinding.ItemCategroyLeftSideBinding
import com.abdo.news.databinding.ItemCategroyRightSideBinding

class CategoriesAdapter(val categories: List<Category>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(
//                if (viewType == isLeftSide) R.layout.item_categroy_left_side
//                else R.layout.item_categroy_right_side, parent, false
//            )
        if (viewType == isLeftSide) {
            var leftSideBinding: ItemCategroyLeftSideBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_categroy_left_side,
                parent, false
            )
            return ViewHolderLeft(leftSideBinding)
        } else {

            var rightSideBinding: ItemCategroyRightSideBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_categroy_right_side, parent, false
            )
            return ViewHolderRight(rightSideBinding)
        }

    }

    val isRightSide = 10
    val isLeftSide = 20
    override fun getItemViewType(position: Int): Int {
        if (position % 2 == 0) return isLeftSide
        return isRightSide
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val category = categories[position]
        if (getItemViewType(position) == isLeftSide) {
            (holder as ViewHolderLeft).bind(category)
        } else {
            (holder as ViewHolderRight).bind(category)
        }
        // holder.parent.setCardBackgroundColor(holder.parent.context.getColor(category.backgroundColor))

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

    class ViewHolderLeft(val view: ItemCategroyLeftSideBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(model: Category) {
            view.model = model
            view.invalidateAll()
        }

    }

    class ViewHolderRight(val view: ItemCategroyRightSideBinding) :
        RecyclerView.ViewHolder(view.root) {

        fun bind(model: Category) {
            view.model = model
            view.invalidateAll()
        }
    }
}