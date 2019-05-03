package com.shvants.runninglife.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.annotation.UiThread
import com.shvants.runninglife.R
import com.shvants.runninglife.data.base.Repository
import com.shvants.runninglife.ui.base.BaseCustomView
import com.shvants.runninglife.ui.model.RunMoveModelUi
import com.shvants.runninglife.utils.Const.*
import kotlinx.android.synthetic.main.base_parameter_view.view.*

open class BaseParameterView
@JvmOverloads
constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : BaseCustomView(context, attrs, defStyleAttr) {

    private lateinit var distanceView: TextView
    private lateinit var timeView: TextView
    private lateinit var tempoView: TextView

//    init {
//        View.inflate(context, getLayoutResId(), this)
//        onViewInflated(context)
//    }

    override fun onViewInflated(context: Context) {
        distanceView = moveDistanceValue
        timeView = moveTimeValue
        tempoView = moveTempoValue
    }

    override fun getLayoutResId(): Int {
        return R.layout.base_parameter_view
    }

    @UiThread
    override fun setView(vararg modelUi: Repository): BaseParameterView {
        val runMove = modelUi[0] as RunMoveModelUi

        distanceView.text = getDistanceAsString(runMove.distance)
        timeView.text = getTimeAsString(runMove.time)
        tempoView.text = getTempoAsString(runMove.time, runMove.distance)

        return this
    }

    private fun getDistanceAsString(distance: Double?): String {
        return if (distance == ZERO_DOUBLE) {
            "$HYPHEN ${resources.getString(R.string.km)}"
        } else {
            val formatDistance = RunMoveView.DISTANCE_FORMAT.format(distance)

            "$formatDistance ${resources.getString(R.string.km)}"
        }
    }

    private fun getTempoAsString(time: Int, distance: Double): String {

        val tempo: String

        tempo = if (distance != ZERO_DOUBLE) {

            val tempoInSeconds = (time / distance).toInt()
            val seconds = tempoInSeconds % RunMoveView.ONE_MINUTE
            val minutes = tempoInSeconds / RunMoveView.ONE_MINUTE

            val mString = if (minutes != ZERO) "$minutes" else EMPTY
            val sString = if (seconds < TEN) "$ZERO$seconds" else "$seconds"

            "$mString$COLON$sString $SLASH${resources.getString(R.string.km)}"
        } else {
            "$HYPHEN$COLON$HYPHEN $SLASH${resources.getString(R.string.km)}"
        }

        return tempo
    }

    private fun getTimeAsString(time: Int): CharSequence? {

        val result: CharSequence?
        val hours = time / RunMoveView.ONE_HOUR
        val minutes = (time - hours * RunMoveView.ONE_HOUR) / RunMoveView.ONE_MINUTE
        val seconds = time % RunMoveView.ONE_MINUTE

        var hString = "$hours ${resources.getString(R.string.hours)}$DOT"
        var mString = "$minutes ${resources.getString(R.string.minutes)}"
        var sString = "$seconds ${resources.getString(R.string.seconds)}$DOT"

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
}