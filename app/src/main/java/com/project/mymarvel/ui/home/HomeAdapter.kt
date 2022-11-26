package com.project.mymarvel.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.mymarvel.databinding.ItemMarvelBinding
import com.project.mymarvel.domain.MarvelItem

class HomeAdapter(private val items: List<MarvelItem> ) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemMarvelBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMarvelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        onBind(holder, position)
    }

    override fun getItemCount(): Int = items.size

    private fun onBind(holder: ViewHolder, position: Int) {
        val item = items[position]
        fillMarvelItem(holder.binding, item)
    }

    private fun fillMarvelItem(binding: ItemMarvelBinding, item: MarvelItem) {
        binding.item = item
    }
}