package com.project.mymarvel.ui.fragments.settings

import android.app.Activity
import android.content.Context
import android.graphics.Typeface
import androidx.core.content.ContextCompat
import com.project.mymarvel.R
import com.project.mymarvel.common.LocaleManager
import com.project.mymarvel.common.utils.toLanguage
import com.project.mymarvel.databinding.ItemLanguageBinding
import com.project.mymarvel.domain.LanguageItem

class SettingsState(private val context: Context, private val activity: Activity) {

    fun onLanguageClick(bind: ItemLanguageBinding, item: LanguageItem) {
        bind.imageView.background =
            ContextCompat.getDrawable(context, R.drawable.language_shadow)
        bind.titleText.setTypeface(null, Typeface.BOLD)
        LocaleManager.newInstance(context).setNewLocale(item.locale)
        loadLanguage()
    }

    fun setLanguages(): List<LanguageItem> {
        return listOf(
            LocaleManager.ENGLISH.toLanguage(),
            LocaleManager.SPANISH.toLanguage()
        )
    }

    private fun loadLanguage() {
       activity.recreate()
    }
}