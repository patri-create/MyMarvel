package com.project.mymarvel.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.project.mymarvel.databinding.LayoutToolbarBinding

class Toolbar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
    CoordinatorLayout(context, attrs, defStyle) {

    private var binding: LayoutToolbarBinding = LayoutToolbarBinding.inflate(
        LayoutInflater.from(context), this, false
    )

    init {
        addView(binding.root)
    }
}