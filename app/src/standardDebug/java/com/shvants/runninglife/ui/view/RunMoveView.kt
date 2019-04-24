package com.shvants.runninglife.ui.view

import android.content.Context
import android.text.format.DateFormat
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.UiThread
import com.shvants.runninglife.R
import com.shvants.runninglife.ui.base.BaseMoveLayout
import com.shvants.runninglife.ui.model.MoveModelUi
import com.shvants.runninglife.ui.model.RunMoveModelUi
import com.shvants.runninglife.ui.model.UserModelUi
import com.shvants.runninglife.utils.Const.*
import kotlinx.android.synthetic.main.base_run_move_view.view.*

class RunMoveView
@JvmOverloads
constructor(
        context: Context,
        attrs: AttributeSet? = NULL,
        defStyleAttr: Int = ZERO) : BaseMoveLayout(context, attrs, defStyleAttr) {

    private lateinit var avatarView: ImageView
    private lateinit var nameView: TextView
    private lateinit var beginTimeView: TextView
    private lateinit var titleView: TextView
    private lateinit var distanceView: TextView
    private lateinit var timeView: TextView
    private lateinit var tempoView: TextView
    private lateinit var imageView: ImageView

    override fun onViewInflated(context: Context) {

        avatarView = moveUserAvatar
        nameView = moveUserFullName
        beginTimeView = moveBeginTime
        titleView = moveTitle
        distanceView = moveDistanceValue
        timeView = moveTimeValue
        imageView = moveImage
        tempoView = moveTempoValue
    }

    @UiThread
    override fun setView(user: UserModelUi, move: MoveModelUi): RunMoveView {

        val runMove = move as RunMoveModelUi
        val avatar = if (user.avatar != ZERO)
            user.avatar
        else
            R.drawable.ic_avatar_stub

        avatarView.setImageResource(avatar)
        nameView.text = user.fullName
        beginTimeView.text = getBeginTimeAsString(runMove.beginTime)
        titleView.text = runMove.title
        distanceView.text = getDistanceAsString(runMove.distance)
        timeView.text = getTimeAsString(runMove.time)
        tempoView.text = getTempoAsString(runMove.time, runMove.distance)

        if (runMove.imgRes != ZERO) {
            imageView.setImageResource(runMove.imgRes)
        } else {
            imageView.visibility = View.GONE
        }

        return this
    }

    private fun getDistanceAsString(distance: Double?): String {
        return if (distance == ZERO_DOUBLE) {
            "$HYPHEN ${resources.getString(R.string.km)}"
        } else {
            val formatDistance = DISTANCE_FORMAT.format(distance)

            "$formatDistance ${resources.getString(R.string.km)}"
        }
    }

    private fun getTempoAsString(time: Int, distance: Double): String {

        var tempo: String

        tempo = if (distance != ZERO_DOUBLE) {

            val tempoInSeconds = (time / distance).toInt()
            val seconds = tempoInSeconds % ONE_MINUTE
            val minutes = tempoInSeconds / ONE_MINUTE

            val mString = if (minutes != ZERO) "$minutes" else EMPTY
            val sString = if (seconds != ZERO) "$seconds" else "$ZERO$ZERO"

            "$mString$COLON$sString $SLASH${resources.getString(R.string.km)}"
        } else {
            "$HYPHEN$COLON$HYPHEN $SLASH${resources.getString(R.string.km)}"
        }

        return tempo
    }

    private fun getTimeAsString(time: Int): CharSequence? {

        val result: CharSequence?
        val hours = time / ONE_HOUR
        val minutes = (time - hours * ONE_HOUR) / ONE_MINUTE
        val seconds = time % ONE_MINUTE

        var hString = "$hours ${resources.getString(R.string.hours)}$DOT "
        var mString = "$minutes ${resources.getString(R.string.minutes)}"
        var sString = "$seconds ${resources.getString(R.string.seconds)}$DOT "

        when {
            hours == ZERO -> hString = EMPTY
            minutes == ZERO -> mString = EMPTY
            seconds == ZERO -> sString = EMPTY
        }

        result = if (hString != EMPTY)
            "$hString $mString"
        else
            "$mString $sString"

        return result
    }

    private fun getBeginTimeAsString(time: Long) = DateFormat.format(DATE_TIME_PATTERN, time)

    override fun getLayoutResId(): Int {
        return R.layout.base_run_move_view
    }

    companion object {
        const val DATE_TIME_PATTERN = "dd MMM yy HH:mm"
        const val DISTANCE_FORMAT = "%.2f"
        const val ONE_MINUTE = 60
        const val ONE_HOUR = 3600
    }
}