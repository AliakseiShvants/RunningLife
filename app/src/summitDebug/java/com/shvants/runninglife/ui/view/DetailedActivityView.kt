package com.shvants.runninglife.ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.UiThread
import com.shvants.runninglife.R
import com.shvants.runninglife.model.base.MetaActivity
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
        defStyleAttr: Int = ZERO) :
        BaseCustomView<DetailedActivityUi>,
        BaseConstraintView(context, attrs, defStyleAttr) {

    override fun getLayoutResId() = R.layout.layout_detailed_activity

    @UiThread
    override fun setView(activity: DetailedActivityUi) {
        when (activity.type) {
            ActivityType.RUN.title -> activityAthleteView.setActivityTypeIcon(R.drawable.ic_run)
            ActivityType.RIDE.title -> activityAthleteView.setActivityTypeIcon(R.drawable.ic_ride)
        }

        activityAthleteView.setStartDate(activity.startDate)

        detailedDataView.setView(activity)
        clubNameTextView.text = activity.name
        kudoersView.setView(activity as MetaActivity)

        val commentCount = activity.commentCount

        val comment = when (commentCount) {
            1 -> resources.getString(R.string.comment)
            in 2..4 -> resources.getString(R.string.two_four_comments)
            else -> resources.getString(R.string.comments)
        }

        if (commentCount > 0) commentsCountTextView.text = "$commentCount $comment"
    }

    @UiThread
    fun setAthleteView(athlete: SummaryAthleteUi) {
        activityAthleteView.setView(athlete)
    }
}