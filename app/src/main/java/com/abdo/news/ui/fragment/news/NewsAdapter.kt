package com.abdo.news.ui.fragment.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.abdo.news.R
import com.abdo.news.api.model.ArticlesItem
import com.abdo.news.databinding.ItemNewsBinding


class NewsAdapter(var items: List<ArticlesItem?>? = null) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding: ItemNewsBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_news,
                parent,
                false
            )
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items!![position];
        holder.bind(item)

    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    fun changeData(data: List<ArticlesItem?>?) {
        items = data
        notifyDataSetChanged()
    }

    class ViewHolder(val itemBinding: ItemNewsBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: ArticlesItem?) {
            itemBinding.item = item
            itemBinding.invalidateAll()
        }
    }


}