package com.project.mymarvel.common.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.project.mymarvel.R
import com.project.mymarvel.domain.Hero
import com.project.mymarvel.domain.MarvelItem
import com.project.mymarvel.ui.home.HomeAdapter
import com.project.mymarvel.ui.home.MarginItemDecoration

@BindingAdapter("loadImage")
fun AppCompatImageView.loadImage(image: String) {
    if(image.isNotEmpty()) {
        val options = RequestOptions().centerCrop()
        Glide.with(context).load(image).apply(options).into(this)
    }
}

@BindingAdapter("items")
fun RecyclerView.setItems(items: List<MarvelItem>?) {
    items?.let {
        addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin)))
        adapter = HomeAdapter(items)
    }
}