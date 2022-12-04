package com.project.mymarvel.data.preferences

import android.content.Context
import android.content.SharedPreferences
import com.project.mymarvel.R
import javax.inject.Inject

class PreferencesStorage @Inject constructor(context: Context) {

    val prefs: SharedPreferences = context.getSharedPreferences(
        context.getString(R.string.preferences_file),
        Context.MODE_PRIVATE
    )
}