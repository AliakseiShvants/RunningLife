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
import com.shvants.runninglife.utils.Const.COMMENTS
import com.shvants.runninglife.utils.Const.ONE_COMMENT
import com.shvants.runninglife.utils.Const.ZERO
import kotlinx.android.synthetic.main.activity_athlete_view.view.*
import kotlinx.android.synthetic.summitDebug.layout_detailed_activity.view.*

class DetailedActivityView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = ZERO
) : BaseCustomView<DetailedActivityUi>, BaseConstraintView(context, attrs, defStyleAttr) {

    override fun getLayoutResId() = R.layout.layout_detailed_activity

    @UiThread
    override fun setView(activity: DetailedActivityUi) {
        when (activity.type) {
            ActivityType.RUN.title -> detailedActivityAthlete.setActivityTypeIcon(R.drawable.ic_run)
            ActivityType.RIDE.title -> detailedActivityAthlete.setActivityTypeIcon(R.drawable.ic_ride)
        }

        detailedActivityAthlete.setStartDate(activity.startDate)

        detailedData.setView(activity)
        detailedActivityName.text = activity.name
        kudoers.seView(activity)

        when (activity.commentCount) {
            0 -> return
            1 -> commentCount.text = ONE_COMMENT
            else -> commentCount.text = "${activity.commentCount} $COMMENTS"
        }
    }

    fun getDetailedAthleteProfile() = detailedActivityAthlete.athleteProfile
}