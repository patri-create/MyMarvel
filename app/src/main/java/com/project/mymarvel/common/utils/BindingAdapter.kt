package com.project.mymarvel.common.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.project.mymarvel.domain.MarvelItem

@BindingAdapter("loadImage")
fun AppCompatImageView.loadImage(item: MarvelItem) {
    if(item.image.isNotEmpty()) {
        val options = RequestOptions().centerCrop()
        Glide.with(context).load(item.image).apply(options).into(this)
    }
}