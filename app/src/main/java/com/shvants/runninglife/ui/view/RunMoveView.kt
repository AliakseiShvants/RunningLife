package com.shvants.runninglife.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.annotation.DrawableRes
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

    override fun onViewInflated(context: Context) {
        super.userAvatar = userAvatar
        super.userFullName = userFullName
        super.startTime = startTime

        super.title = moveTitle
        super.distance = moveDistanceValue
        super.time = moveTimeValue

        tempo = moveTempoValue
    }

    fun setUserAvatar(@DrawableRes avatarId: Int): RunMoveView {
        userAvatar?.setImageResource(avatarId)

        return this
    }

    fun setUserFullName(fullName: String): RunMoveView {
        userFullName?.text = fullName

        return this
    }

    fun setStartTime(startTime: Long): RunMoveView {
        userFullName?.text = getStartTimeAsString(startTime)

        return this
    }

    private fun getStartTimeAsString(startTime: Long): CharSequence? {

    }

    override fun getLayoutResId(): Int {
        return R.layout.move_view
    }
}