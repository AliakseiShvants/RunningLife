package com.shvants.runninglife.ui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.UiThread
import com.shvants.runninglife.R
import com.shvants.runninglife.model.gson.ActivityType
import com.shvants.runninglife.model.ui.DetailedActivityUi
import com.shvants.runninglife.model.ui.SummaryAthleteUi
import com.shvants.runninglife.ui.view.base.BaseConstraintView
import com.shvants.runninglife.ui.view.base.BaseCustomView
import com.shvants.runninglife.utils.Const.ZERO
import kotlinx.android.synthetic.summitDebug.layout_detailed_activity.view.*

class DetailedActivityView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = ZERO) : BaseCustomView<DetailedActivityUi>,
        BaseConstraintView(context, attrs, defStyleAttr) {

    private lateinit var athleteView: ActivityAthleteView
    private lateinit var nameView: TextView
    private lateinit var dataView: DetailedDataView
    private lateinit var likeView: LikeView
    private lateinit var mapView: ImageView

    override fun onViewInflated(context: Context) {
        athleteView = detailedActivityAthlete
        nameView = detailedActivityName
        dataView = detailedData
        likeView = detailedLikePanel
        mapView = detailedActivityMap
    }

    override fun getLayoutResId() = R.layout.layout_detailed_activity

    @UiThread
    override fun setView(activity: DetailedActivityUi) {
        when (activity.type) {
            ActivityType.RUN.title -> athleteView.setActivityTypeIcon(R.drawable.ic_run)
            ActivityType.RIDE.title -> athleteView.setActivityTypeIcon(R.drawable.ic_ride)
        }

        athleteView.setStartDate(activity.startDate)

        dataView.setView(activity)
        nameView.text = activity.name
        likeView.seView(activity)
    }

    @UiThread
    fun setAthleteView(athlete: SummaryAthleteUi) {
        athleteView.setView(athlete)
    }

    fun getDetailedAthleteProfile() = athleteView.getProfileView()
}