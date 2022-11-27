package com.project.mymarvel.common.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.project.mymarvel.domain.EventItem
import com.project.mymarvel.domain.MarvelItem
import com.project.mymarvel.ui.home.adapters.EventAdapter
import com.project.mymarvel.ui.home.adapters.HomeAdapter
import ru.tinkoff.scrollingpagerindicator.ScrollingPagerIndicator

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
        adapter = HomeAdapter(items)
    }
}

@BindingAdapter(value = ["events", "indicator"], requireAll = true)
fun RecyclerView.setEvents(items: List<EventItem>?, indicator: ScrollingPagerIndicator) {
    items?.let {
        adapter = EventAdapter(items)
        indicator.attachToRecyclerView(this)

    }
}