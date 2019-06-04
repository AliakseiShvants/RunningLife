package com.shvants.runninglife.ui.view.base

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.shvants.runninglife.utils.Const.NULL
import com.shvants.runninglife.utils.Const.ZERO

abstract class BaseConstraintView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = NULL,
        defStyleAttr: Int = ZERO) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, getLayoutResId(), this)
        onViewInflated(context)
    }

    @LayoutRes
    abstract fun getLayoutResId(): Int

    abstract fun onViewInflated(context: Context)
}