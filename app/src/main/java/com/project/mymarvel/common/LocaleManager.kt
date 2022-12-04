package com.project.mymarvel.common

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import com.project.mymarvel.App
import com.project.mymarvel.data.PreferencesRepository
import com.project.mymarvel.data.preferences.PreferencesDataSourceImp
import com.project.mymarvel.data.preferences.PreferencesStorage
import com.project.mymarvel.usecases.FindPreferencesUseCase
import com.project.mymarvel.usecases.SavePreferencesUseCase
import java.util.*

class LocaleManager {

    private lateinit var context: Context

    private val findPreferencesUseCase: FindPreferencesUseCase by lazy { initFindPreferences() }
    private val savePreferencesUseCase: SavePreferencesUseCase by lazy { initSavePreferences() }

   fun setLocale(): Context {
        setNewLocale(getLanguage())
        return context
    }

    fun setNewLocale(language: String) {
        persistLanguage(language)
        updateResources(language)
    }

    fun getLanguage(): String {
        return findPreferencesUseCase(key)
    }

    fun persistLanguage(language: String) {
        savePreferencesUseCase(key, language)
    }

    private fun updateResources(language: String): Context? {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val res: Resources = context.resources
        val config = Configuration(res.configuration)
        config.setLocale(locale)
        return context.createConfigurationContext(config)
    }

    private fun initSavePreferences(): SavePreferencesUseCase {
        return SavePreferencesUseCase(PreferencesRepository(PreferencesDataSourceImp(
            PreferencesStorage(context)
        )))
    }

    private fun initFindPreferences(): FindPreferencesUseCase {
        return FindPreferencesUseCase(PreferencesRepository(PreferencesDataSourceImp(
            PreferencesStorage(context)
        )))
    }

    companion object {
        private const val key = "currentLanguage"
        @JvmStatic
        fun newInstance(c: Context) =
            LocaleManager().apply {
                context = c
        }
    }
}