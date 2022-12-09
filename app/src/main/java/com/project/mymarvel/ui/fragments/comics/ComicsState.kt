package com.project.mymarvel.ui.fragments.comics

import android.content.Context
import androidx.navigation.NavController
import com.project.mymarvel.R
import com.project.mymarvel.domain.Error
import com.project.mymarvel.domain.MarvelItem

class ComicsState(private val context: Context, private val navController: NavController) {

    fun onItemClick(item: MarvelItem) {
        val action = ComicsFragmentDirections.actionComicsDestToDetailDest(item)
        navController.navigate(action)
    }

    fun errorToString(error: Error) = when (error) {
        Error.Connectivity -> context.getString(R.string.error_connectivity)
        is Error.Server -> context.getString(R.string.error_server)
        is Error.Unknown -> context.getString(R.string.error_unknown)
    }
}