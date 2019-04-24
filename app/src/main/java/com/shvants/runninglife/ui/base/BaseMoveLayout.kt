package com.shvants.runninglife.ui.base

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
        defStyleAttr: Int = ZERO) : ConstraintLayout(context, attrs, defStyleAttr),
                                    IView{
//    abstract var avatarView: ImageView
//    abstract var nameView: TextView
//    abstract var beginTimeView: TextView
//    abstract var titleView: TextView
//    abstract var distanceView: TextView
//    abstract var timeView: TextView
//    abstract var imageView: ImageView

    init {
        val context = getContext()

        View.inflate(context, getLayoutResId(), this)
        onViewInflated(context)
    }
}