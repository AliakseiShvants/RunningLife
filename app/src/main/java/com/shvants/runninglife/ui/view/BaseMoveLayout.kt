package com.shvants.runninglife.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.shvants.runninglife.utils.Const.NULL
import com.shvants.runninglife.utils.Const.ZERO

abstract class BaseMoveLayout
@JvmOverloads
constructor(
        context: Context,
        attrs: AttributeSet? = NULL,
        defStyleAttr: Int = ZERO) : ConstraintLayout(context, attrs, defStyleAttr), IView {

    abstract var userView: UserView

    init {
        View.inflate(context, getLayoutResId(), this)
        onViewInflated(context)
    }
}