package com.shvants.runninglife.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.annotation.UiThread
import androidx.constraintlayout.widget.ConstraintLayout
import com.shvants.runninglife.R
import com.shvants.runninglife.ui.model.BaseModelUi
import com.shvants.runninglife.ui.model.RunMoveModelUi
import com.shvants.runninglife.utils.Const
import kotlinx.android.synthetic.main.base_parameter_view.view.*

open class BaseParameterView
@JvmOverloads
constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), IView {

    private lateinit var distanceView: TextView
    private lateinit var timeView: TextView
    private lateinit var tempoView: TextView

    init {
        View.inflate(context, getLayoutResId(), this)
        onViewInflated(context)
    }

    override fun onViewInflated(context: Context) {
        distanceView = moveDistanceValue
        timeView = moveTimeValue
        tempoView = moveTempoValue
    }

    override fun getLayoutResId(): Int {
        return R.layout.base_parameter_view
    }

    @UiThread
    override fun setView(vararg modelUi: BaseModelUi): BaseParameterView {
        val runMove = modelUi[0] as RunMoveModelUi

        distanceView.text = getDistanceAsString(runMove.distance)
        timeView.text = getTimeAsString(runMove.time)
        tempoView.text = getTempoAsString(runMove.time, runMove.distance)

        return this
    }

    private fun getDistanceAsString(distance: Double?): String {
        return if (distance == Const.ZERO_DOUBLE) {
            "${Const.HYPHEN} ${resources.getString(R.string.km)}"
        } else {
            val formatDistance = RunMoveView.DISTANCE_FORMAT.format(distance)

            "$formatDistance ${resources.getString(R.string.km)}"
        }
    }

    private fun getTempoAsString(time: Int, distance: Double): String {

        val tempo: String

        tempo = if (distance != Const.ZERO_DOUBLE) {

            val tempoInSeconds = (time / distance).toInt()
            val seconds = tempoInSeconds % RunMoveView.ONE_MINUTE
            val minutes = tempoInSeconds / RunMoveView.ONE_MINUTE

            val mString = if (minutes != Const.ZERO) "$minutes" else Const.EMPTY
            val sString = if (seconds != Const.ZERO) "$seconds" else "${Const.ZERO}${Const.ZERO}"

            "$mString${Const.COLON}$sString ${Const.SLASH}${resources.getString(R.string.km)}"
        } else {
            "${Const.HYPHEN}${Const.COLON}${Const.HYPHEN} ${Const.SLASH}${resources.getString(R.string.km)}"
        }

        return tempo
    }

    private fun getTimeAsString(time: Int): CharSequence? {

        val result: CharSequence?
        val hours = time / RunMoveView.ONE_HOUR
        val minutes = (time - hours * RunMoveView.ONE_HOUR) / RunMoveView.ONE_MINUTE
        val seconds = time % RunMoveView.ONE_MINUTE

        var hString = "$hours ${resources.getString(R.string.hours)}${Const.DOT} "
        var mString = "$minutes ${resources.getString(R.string.minutes)}"
        var sString = "$seconds ${resources.getString(R.string.seconds)}${Const.DOT} "

        when {
            hours == Const.ZERO -> hString = Const.EMPTY
            minutes == Const.ZERO -> mString = Const.EMPTY
            seconds == Const.ZERO -> sString = Const.EMPTY
        }

        result = if (hString != Const.EMPTY)
            "$hString $mString"
        else
            "$mString $sString"

        return result
    }
}