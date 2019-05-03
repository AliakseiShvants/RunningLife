package com.shvants.runninglife.ui.base

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.shvants.runninglife.data.base.Repository
import com.shvants.runninglife.utils.Const.NULL
import com.shvants.runninglife.utils.Const.ZERO

abstract class BaseCustomView
@JvmOverloads
constructor(
        context: Context,
        attrs: AttributeSet? = NULL,
        defStyleAttr: Int = ZERO) : ConstraintLayout(context, attrs, defStyleAttr), BaseView {

    init {
        View.inflate(context, getLayoutResId(), this)
        onViewInflated(context)
    }

    abstract fun setView(vararg data: Repository): BaseCustomView
}