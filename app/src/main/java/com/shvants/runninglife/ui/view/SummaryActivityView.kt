package com.shvants.runninglife.ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.UiThread
import com.shvants.runninglife.R
import com.shvants.runninglife.model.base.MetaActivity
import com.shvants.runninglife.model.gson.ActivityType
import com.shvants.runninglife.model.ui.SummaryActivityUi
import com.shvants.runninglife.model.ui.SummaryAthleteUi
import com.shvants.runninglife.ui.view.base.BaseConstraintView
import com.shvants.runninglife.ui.view.base.BaseCustomView
import com.shvants.runninglife.utils.Const.ZERO
import kotlinx.android.synthetic.main.layout_summary_activity.view.*

open class SummaryActivityView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = ZERO) : BaseCustomView<SummaryActivityUi>,
        BaseConstraintView(context, attrs, defStyleAttr) {

    @UiThread
    override fun setView(activity: SummaryActivityUi) {
        when (activity.type) {
            ActivityType.RUN.title -> activityAthleteView.setActivityTypeIcon(R.drawable.ic_run)
            ActivityType.RIDE.title -> activityAthleteView.setActivityTypeIcon(R.drawable.ic_ride)
        }

        activityAthleteView.setStartDate(activity.startDate)

        summaryDataView.setView(activity)
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

    override fun getLayoutResId() = R.layout.layout_summary_activity
}