package com.shvants.runninglife.ui.view.base

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.shvants.runninglife.utils.Const.NULL
import com.shvants.runninglife.utils.Const.ZERO

abstract class BaseLinearView
@JvmOverloads
constructor(
        context: Context,
        attrs: AttributeSet? = NULL,
        defStyleAttr: Int = ZERO) : LinearLayout(context, attrs, defStyleAttr), BaseCustomView {

    init {
        View.inflate(context, getLayoutResId(), this)
        onViewInflated(context)
    }
}