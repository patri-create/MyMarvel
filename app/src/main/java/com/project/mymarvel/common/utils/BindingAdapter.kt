package com.project.mymarvel.common.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.project.mymarvel.domain.EventItem
import com.project.mymarvel.domain.MarvelItem
import com.project.mymarvel.ui.adapters.EventAdapter
import com.project.mymarvel.ui.adapters.MarvelAdapter
import ru.tinkoff.scrollingpagerindicator.ScrollingPagerIndicator

@BindingAdapter("loadImage")
fun AppCompatImageView.loadImage(image: String) {
    if (image.isNotEmpty()) {
        val options = RequestOptions().centerCrop()
        Glide.with(context).load(image).apply(options).into(this)
    }
}

@BindingAdapter("items")
fun RecyclerView.setItems(items: List<MarvelItem>?) {
    items?.let {
        adapter = MarvelAdapter(items)
    }
}

@BindingAdapter(value = ["events", "indicator"], requireAll = true)
fun RecyclerView.setEvents(items: List<EventItem>?, indicator: ScrollingPagerIndicator) {
    items?.let {
        adapter = EventAdapter(items)
        indicator.attachToRecyclerView(this)

    }
}

@BindingAdapter("convertDrawable")
fun AppCompatImageView.convertDrawable(image: String) {
    if (image.isNotEmpty()) {
        val resId = resources.getIdentifier(image, "drawable", context.packageName)
        setImageDrawable(ContextCompat.getDrawable(context, resId))
    }
}