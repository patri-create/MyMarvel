package com.project.mymarvel

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.project.mymarvel.common.LocaleManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleManager.newInstance(base).setLocale())
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleManager.newInstance(applicationContext).setLocale()
    }

    companion object {
        lateinit var instance: App
    }
}
