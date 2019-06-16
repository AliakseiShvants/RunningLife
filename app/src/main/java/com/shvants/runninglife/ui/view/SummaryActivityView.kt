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
import com.shvants.runninglife.utils.Const.ZERO
import kotlinx.android.synthetic.main.layout_summary_activity.view.*

class SummaryActivityView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = ZERO) : BaseCustomView<SummaryActivityUi>,
        BaseConstraintView(context, attrs, defStyleAttr) {

    private lateinit var athleteView: ActivityAthleteView
    private lateinit var nameView: TextView
    private lateinit var dataView: SummaryDataView
    private lateinit var kudoersView: KudoersView
    private lateinit var commentCountView: TextView
    private lateinit var mapView: ImageView

    override fun onViewInflated(context: Context) {
        athleteView = activityAthlete
        nameView = activityName
        dataView = summaryActivityData
        kudoersView = kudoers
        commentCountView = commentCount
        mapView = summaryActivityMap
    }

    @UiThread
    override fun setView(activity: SummaryActivityUi) {
        when (activity.type) {
            ActivityType.RUN.title -> athleteView.setActivityTypeIcon(R.drawable.ic_run)
            ActivityType.RIDE.title -> athleteView.setActivityTypeIcon(R.drawable.ic_ride)
        }

        athleteView.setStartDate(activity.startDate)

        dataView.setView(activity)
        nameView.text = activity.name
        kudoersView.setView(activity)

        when (activity.commentCount) {
            0 -> return
            1 -> {
                commentCountView.text = ONE_COMMENT
            }
            else -> {
                val commentCount = "${activity.commentCount} $COMMENTS"
                commentCountView.text = commentCount
            }
        }
    }

    @UiThread
    fun setAthleteView(athlete: SummaryAthleteUi) {
        athleteView.setView(athlete)
    }

    override fun getLayoutResId() = R.layout.layout_summary_activity

    companion object {
        private const val ONE_COMMENT = "1 comment"
        private const val COMMENTS = "comments"
    }
}