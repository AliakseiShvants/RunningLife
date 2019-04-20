package com.shvants.runninglife.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import com.shvants.runninglife.R
import com.shvants.runninglife.ui.base.BaseMoveLayout
import com.shvants.runninglife.utils.Const.NULL
import com.shvants.runninglife.utils.Const.ZERO
import kotlinx.android.synthetic.main.move_view.view.*

class RunMoveView
@JvmOverloads
constructor(
        context: Context,
        attrs: AttributeSet? = NULL,
        defStyleAttr: Int = ZERO) : BaseMoveLayout(context, attrs, defStyleAttr) {

    private var tempo: TextView? = null

    init {
        View.inflate(context, getLayoutResId(), this)
        init()
    }

    private fun init() {
        super.userView = moveUserView

    }

    override fun onViewInflated(context: Context) {
        super.title = moveTitle
        super.distance = moveDistanceValue
        super.time = moveTimeValue

        tempo = moveTempoValue
    }

    override fun getLayoutResId(): Int {
        return R.layout.move_view
    }
}