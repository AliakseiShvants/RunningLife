package com.shvants.runninglife.ui.base

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.shvants.runninglife.utils.Const.NULL
import com.shvants.runninglife.utils.Const.ZERO

abstract class BaseConstraintView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = NULL,
        defStyleAttr: Int = ZERO) : ConstraintLayout(context, attrs, defStyleAttr), BaseCustomView {

    init {
        View.inflate(context, getLayoutResId(), this)
        onViewInflated(context)
    }
}