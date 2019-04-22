package com.shvants.runninglife.ui.base

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.shvants.runninglife.ui.view.UserView
import com.shvants.runninglife.utils.Const.NULL
import com.shvants.runninglife.utils.Const.ZERO

abstract class BaseMoveLayout
@JvmOverloads
constructor(
        context: Context,
        attrs: AttributeSet? = NULL,
        defStyleAttr: Int = ZERO) : ConstraintLayout(context, attrs, defStyleAttr),
                                    IView{
    var userAvatar: ImageView? = null
    var userFullName: TextView? = null
    var startTime: TextView? = null
    var title: TextView? = null
    var distance: TextView? = null
    var time: TextView? = null
    var elevation: TextView? = null
    var calories: TextView? = null
    var avgHr: TextView? = null

    init {
        View.inflate(context, getLayoutResId(), this)
    }
}