package com.project.mymarvel.ui.adapters

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.mymarvel.databinding.ItemLanguageBinding
import com.project.mymarvel.domain.LanguageItem

class LanguageAdapter(
    private val items: List<LanguageItem>,
    private val listenerOnClickLanguage: (ItemLanguageBinding) -> Unit
) :
    RecyclerView.Adapter<LanguageAdapter.ViewHolder>() {

    private var lastSelectedLanguage: ViewHolder? = null

    class ViewHolder(val binding: ItemLanguageBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemLanguageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        onBind(holder, position)
    }

    override fun getItemCount(): Int = items.size

    private fun onBind(holder: ViewHolder, position: Int) {
        val item = items[position]
        fillLanguageItem(holder.binding, item, holder)
    }

    private fun fillLanguageItem(binding: ItemLanguageBinding, item: LanguageItem, holder: ViewHolder) {
        binding.item = item
        binding.languageContainer.setOnClickListener {
            resetLastLanguageSelected()
            listenerOnClickLanguage(binding)
            lastSelectedLanguage = holder
        }
    }

    private fun resetLastLanguageSelected() {
        lastSelectedLanguage?.let {
            it.binding.imageView.background = null
            it.binding.titleText.setTypeface(null, Typeface.NORMAL)
        }
    }
}