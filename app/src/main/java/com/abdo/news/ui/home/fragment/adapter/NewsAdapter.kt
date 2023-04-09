package com.abdo.news.ui.home.fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abdo.news.R
import com.abdo.news.api.model.ArticlesItem
import com.bumptech.glide.Glide


class NewsAdapter(var items: List<ArticlesItem?>? = null) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsAdapter.ViewHolder, position: Int) {
        val item = items!![position];
        holder.title.setText(item?.title)
        holder.author.setText(item?.author)
        holder.date.setText(item?.publishedAt)
        Glide.with(holder.imageView)
            .load(item?.urlToImage)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    fun changeData(data: List<ArticlesItem?>?) {
        items = data
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val progressBar: ProgressBar = itemView.findViewById(R.id.progress_bar)
        val imageView: ImageView = itemView.findViewById(R.id.image)
        val author: TextView = itemView.findViewById(R.id.author)
        val title: TextView = itemView.findViewById(R.id.title)
        val date: TextView = itemView.findViewById(R.id.date_time)
    }


}