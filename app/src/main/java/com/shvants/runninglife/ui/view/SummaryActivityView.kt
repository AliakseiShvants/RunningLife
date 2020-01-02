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
import com.shvants.runninglife.utils.Const.COMMENTS
import com.shvants.runninglife.utils.Const.ONE_COMMENT
import com.shvants.runninglife.utils.Const.ZERO
import kotlinx.android.synthetic.main.layout_summary_activity.view.*

class SummaryActivityView(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = ZERO
) : BaseCustomView<SummaryActivityUi>, BaseConstraintView(context, attrs, defStyleAttr) {

    @UiThread
    override fun setView(activity: SummaryActivityUi) {
        when (activity.type) {
            ActivityType.RUN.title -> activityAthlete.setActivityTypeIcon(R.drawable.ic_run)
            ActivityType.RIDE.title -> activityAthlete.setActivityTypeIcon(R.drawable.ic_ride)
        }

        activityAthlete.setStartDate(activity.startDate)

        summaryActivityData.setView(activity)
        activityName.text = activity.name
        kudoers.setView(activity)

        when (activity.commentCount) {
            0 -> return
            1 -> commentCount.text = ONE_COMMENT
            else -> commentCount.text = "${activity.commentCount} $COMMENTS"
        }
    }

    override fun getLayoutResId() = R.layout.layout_summary_activity
}