package com.abdo.news.ui.fragment.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView

@BindingAdapter("imageUrl")
fun loadImageFromURL(imageView: ImageView, url: String) {
    Glide.with(imageView)
        .load(url)
        .into(imageView)
}

@BindingAdapter("app:imageResource")
fun setImageResource(imageView: ImageView, id: Int) {
    imageView.setImageResource(id)
}

@BindingAdapter("app:changeBackgroundColor")
fun changeBackgroundColor(cardView: MaterialCardView, color: Int) {
    // holder.parent.setCardBackgroundColor(holder.parent.context.getColor(category.backgroundColor))
    cardView.setCardBackgroundColor(cardView.context.getColor(color))
}