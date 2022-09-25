package com.project.mymarvel.ui.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.project.mymarvel.R
import com.project.mymarvel.databinding.LayoutToolbarBinding

class Toolbar constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : RelativeLayout(context, attrs, defStyle) {

    private var binding: LayoutToolbarBinding = LayoutToolbarBinding.inflate(
        LayoutInflater.from(context), this, false
    )

    init {
        addView(binding.root)
        actions()
    }

    private fun actions() {}

}