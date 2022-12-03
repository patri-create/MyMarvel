package com.project.mymarvel.ui.comics

import android.content.Context
import com.project.mymarvel.R
import com.project.mymarvel.domain.Error

class ComicsState(private val context: Context) {

    fun errorToString(error: Error) = when(error) {
        Error.Connectivity -> context.getString(R.string.error_connectivity)
        is Error.Server -> context.getString(R.string.error_server)
        is Error.Unknown -> context.getString(R.string.error_unknown)
    }
}