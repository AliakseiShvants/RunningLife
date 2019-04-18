package com.shvants.runninglife.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import com.shvants.runninglife.R
import com.shvants.runninglife.ui.base.BaseMoveView
import com.shvants.runninglife.utils.Const.NULL
import com.shvants.runninglife.utils.Const.ZERO
import kotlinx.android.synthetic.main.move_view.view.*

class RunMoveView2
@JvmOverloads
constructor(
        context: Context,
        attrs: AttributeSet? = NULL,
        defStyleAttr: Int = ZERO) : BaseMoveView(context, attrs, defStyleAttr) {

    private var avgTempo: TextView? = null

    init {
        View.inflate(context, R.layout.move_view, this)
        init()
    }

    private fun init() {
        super.userView = moveUserView
        super.title = moveTitle
        super.distance = moveDistanceValue
        super.time = moveTimeValue
        avgTempo = moveTempoValue
    }
}