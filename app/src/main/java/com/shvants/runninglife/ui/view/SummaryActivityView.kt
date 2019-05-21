package com.shvants.runninglife.ui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.UiThread
import com.shvants.runninglife.R
import com.shvants.runninglife.model.ui.SummaryActivityUi
import com.shvants.runninglife.model.ui.SummaryAthleteUi
import com.shvants.runninglife.ui.view.base.BaseConstraintView
import com.shvants.runninglife.utils.Const.NULL
import com.shvants.runninglife.utils.Const.ZERO
import kotlinx.android.synthetic.main.layout_summary_item.view.*

class SummaryActivityView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = NULL,
        defStyleAttr: Int = ZERO) : BaseConstraintView(context, attrs, defStyleAttr) {

    private lateinit var athleteView: ActivityAthleteView
    private lateinit var nameView: TextView
    private lateinit var dataView: SummaryDataView
    private lateinit var mapView: ImageView

    override fun onViewInflated(context: Context) {
        athleteView = activityAthlete
        nameView = activityName
        dataView = summaryActivityData
        mapView = summaryActivityMap
    }

    @UiThread
    override fun setView(vararg data: Any?): BaseConstraintView {
        val athlete = data[0] as SummaryAthleteUi
        val activity = data[1] as SummaryActivityUi

        athleteView.setView(athlete, activity)
        dataView.setView(activity.distance, activity.avgSpeed, activity.movingTime)
        nameView.text = activity.name
        //todo load map
        mapView.setImageResource(R.drawable.move0)

        return this
    }

    override fun getLayoutResId() = R.layout.layout_summary_item

    companion object {
        const val DATE_TIME_PATTERN = "dd MMM yy HH:mm"
        const val DISTANCE_FORMAT = "%.2f"
        const val ONE_MINUTE = 60
        const val ONE_HOUR = 3600
    }
}