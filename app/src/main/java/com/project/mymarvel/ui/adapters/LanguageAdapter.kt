package com.project.mymarvel.ui.adapters

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.project.mymarvel.R
import com.project.mymarvel.common.LocaleManager
import com.project.mymarvel.common.utils.toLanguage
import com.project.mymarvel.databinding.ItemLanguageBinding
import com.project.mymarvel.domain.LanguageItem

class LanguageAdapter(
    private val items: List<LanguageItem>,
    private val listenerOnClickLanguage: (ItemLanguageBinding, LanguageItem) -> Unit
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
        setDefaultLanguage(item, holder)
        fillLanguageItem(holder.binding, item, holder)
    }

    private fun setDefaultLanguage(item: LanguageItem, holder: ViewHolder) {
        with(holder.binding) {
            val context = imageView.context
            val currentLanguage = LocaleManager.newInstance(context).getLanguage().toLanguage()

            if (item != currentLanguage)
                return

            imageView.background = ContextCompat.getDrawable(context, R.drawable.language_shadow)
            titleText.setTypeface(null, Typeface.BOLD)
            lastSelectedLanguage = holder
        }
    }

    private fun fillLanguageItem(
        binding: ItemLanguageBinding,
        item: LanguageItem,
        holder: ViewHolder
    ) {
        binding.item = item
        binding.languageContainer.setOnClickListener {
            resetLastLanguageSelected()
            listenerOnClickLanguage(binding, item)
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