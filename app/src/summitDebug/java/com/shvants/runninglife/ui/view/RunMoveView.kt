package com.shvants.runninglife.ui.view

import android.content.Context
import android.text.format.DateFormat
import android.util.AttributeSet
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.shvants.runninglife.R
import com.shvants.runninglife.ui.base.BaseMoveLayout
import com.shvants.runninglife.utils.Const.*
import kotlinx.android.synthetic.main.run_move_view.view.*

class RunMoveView
@JvmOverloads
constructor(
        context: Context,
        attrs: AttributeSet? = NULL,
        defStyleAttr: Int = ZERO) : BaseMoveLayout(context, attrs, defStyleAttr) {

    var elevationView: TextView? = null
    var caloriesView: TextView? = null
    var hrView: TextView? = null
    var tempoView: TextView? = null

    override fun onViewInflated(context: Context) {
        super.avatarView = moveUserAvatar
        super.nameView = moveUserFullName
        super.beginTimeView = moveBeginTime
        super.titleView = moveTitle
        super.distanceView = moveDistanceValue
        super.timeView = moveTimeValue
        super.imageView = moveImage
        tempoView = moveTempoValue
    }

    fun setUserAvatar(@DrawableRes avatarId: Int): RunMoveView {
        avatarView?.setImageResource(avatarId)

        return this
    }

    fun setUserFullName(fullName: String): RunMoveView {
        nameView?.text = fullName

        return this
    }

    fun setBeginTime(time: Long): RunMoveView {
        beginTimeView?.text = getBeginTimeAsString(time)

        return this
    }

    fun setMoveTitle(title: String): RunMoveView {
        titleView?.text = title

        return this
    }

    fun setDistanceTitle(distance: Double?): RunMoveView {
        val distanceAsString = getDistanceAsString(distance)
        distanceView?.text = distanceAsString

        return this
    }

    fun setTimeTitle(time: Int): RunMoveView {
        val timeAsString = getTimeAsString(time)
        timeView?.text = timeAsString

        return this
    }

    fun setTempo(distance: Double, time: Int): RunMoveView {
        tempoView?.text = getTempoAsString(time, distance)

        return this
    }

    fun setImage(@DrawableRes imageResId: Int): RunMoveView {
        imageView?.setImageResource(imageResId)

        return this
    }

    private fun getDistanceAsString(distance: Double?) =
            if (distance == ZERO_DOUBLE) {
                "$HYPHEN ${R.string.km}"
            } else {
                "$distance ${R.string.km}"
            }

    private fun getTempoAsString(time: Int, distance: Double): String {
        val tempoInSeconds = time / distance
        val seconds = tempoInSeconds % ONE_MINUTE
        val minutes = tempoInSeconds / ONE_MINUTE

        return "$minutes:$seconds /${R.string.km}"
    }

    private fun getTimeAsString(time: Int): CharSequence? {
        val result: CharSequence?
        val minutes = time % ONE_MINUTE
        val hours = time / ONE_HOUR

        result =
                if (hours == ZERO) {
                    "$minutes ${R.string.minutes}"
                } else {
                    "$hours ${R.string.hours} $minutes ${R.string.minutes}"
                }

        return result
    }

    private fun getBeginTimeAsString(time: Long) = DateFormat.format(DATE_TIME_PATTERN, time)

    override fun getLayoutResId(): Int {
        return R.layout.run_move_view
    }

    companion object {
        const val DATE_TIME_PATTERN = "dd MMM YY HH:mm"
        const val ONE_MINUTE = 60
        const val ONE_HOUR = 3600
    }
}