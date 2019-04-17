package com.shvants.runninglife.ui.base

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.shvants.runninglife.utils.Const.NULL
import com.shvants.runninglife.utils.Const.ZERO
import com.shvants.runninglife.view.UserView

open class BaseMoveView
@JvmOverloads
constructor(
        context: Context,
        attrs: AttributeSet? = NULL,
        defStyleAttr: Int = ZERO) : ConstraintLayout(context, attrs, defStyleAttr) {

    var userView: UserView? = null
    var title: TextView? = null
    var distance: TextView? = null
    var time: TextView? = null
    var elevation: TextView? = null
    var calories: TextView? = null
    var avgHr: TextView? = null
}