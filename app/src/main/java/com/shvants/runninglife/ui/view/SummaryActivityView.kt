package com.shvants.runninglife.ui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.UiThread
import com.shvants.runninglife.R
import com.shvants.runninglife.model.gson.ActivityType
import com.shvants.runninglife.model.ui.SummaryActivityUi
import com.shvants.runninglife.model.ui.SummaryAthleteUi
import com.shvants.runninglife.ui.view.base.BaseConstraintView
import com.shvants.runninglife.ui.view.base.BaseCustomView
import com.shvants.runninglife.utils.Const.NULL
import com.shvants.runninglife.utils.Const.ZERO
import kotlinx.android.synthetic.main.layout_summary_item.view.*

class SummaryActivityView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = NULL,
        defStyleAttr: Int = ZERO) : BaseCustomView<SummaryActivityUi>,
        BaseConstraintView(context, attrs, defStyleAttr) {

    private lateinit var athleteView: ActivityAthleteView
    private lateinit var nameView: TextView
    private lateinit var dataView: SummaryDataView
    private lateinit var likeView: LikeView
    private lateinit var mapView: ImageView

    override fun onViewInflated(context: Context) {
        athleteView = activityAthlete
        nameView = activityName
        dataView = summaryActivityData
        likeView = likePanel
        mapView = summaryActivityMap
    }

    @UiThread
    override fun setView(activity: SummaryActivityUi?) {
        if (activity != null) {
            when (activity.type) {
                ActivityType.RUN.title -> athleteView.setActivityTypeIcon(R.drawable.ic_run)
                ActivityType.RIDE.title -> athleteView.setActivityTypeIcon(R.drawable.ic_ride)
            }

            athleteView.setStartDate(activity.startDate)

            dataView.setView(activity)
            nameView.text = activity.name
            likeView.setView(activity)
        }
    }


    @UiThread
    fun setAthleteView(athlete: SummaryAthleteUi) {
        athleteView.setView(athlete)
    }

    override fun getLayoutResId() = R.layout.layout_summary_item

    companion object {
        const val DATE_TIME_PATTERN = "dd MMM yy HH:mm"
        const val DISTANCE_FORMAT = "%.2f"
        const val ONE_MINUTE = 60
        const val ONE_HOUR = 3600
    }
}