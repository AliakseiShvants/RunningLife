package com.shvants.runninglife.ui.base

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.shvants.runninglife.utils.Const.NULL
import com.shvants.runninglife.utils.Const.ZERO

abstract class BaseMoveLayout
@JvmOverloads
constructor(
        context: Context,
        attrs: AttributeSet? = NULL,
        defStyleAttr: Int = ZERO) : ConstraintLayout(context, attrs, defStyleAttr),
                                    IView{
    var avatarView: ImageView? = null
    var nameView: TextView? = null
    var beginTimeView: TextView? = null
    var titleView: TextView? = null
    var distanceView: TextView? = null
    var timeView: TextView? = null
    var imageView: ImageView? = null

    init {
        val context = getContext()

        View.inflate(context, getLayoutResId(), this)
        onViewInflated(context)
    }
}