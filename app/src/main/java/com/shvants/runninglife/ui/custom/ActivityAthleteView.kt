package com.shvants.runninglife.ui.custom

import android.content.Context
import android.text.format.DateFormat
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import com.shvants.runninglife.R
import com.shvants.runninglife.data.web.model.ActivityType
import com.shvants.runninglife.ui.base.BaseConstraintView
import com.shvants.runninglife.ui.base.BaseView
import com.shvants.runninglife.ui.model.SummaryActivityUi
import com.shvants.runninglife.ui.model.SummaryAthleteUi
import com.shvants.runninglife.utils.Const.*
import kotlinx.android.synthetic.main.activity_athlete_view.view.*
import java.text.SimpleDateFormat
import java.util.*

class ActivityAthleteView
@JvmOverloads
constructor(
        context: Context,
        attrs: AttributeSet? = NULL,
        defStyleAttr: Int = ZERO) : BaseConstraintView(context, attrs, defStyleAttr), BaseView {

    private lateinit var profileView: ImageView
    private lateinit var fullNameView: TextView
    private lateinit var activityTypeIconView: ImageView
    private lateinit var startDateView: TextView

    override fun onViewInflated(context: Context) {
        profileView = athleteProfile
        fullNameView = athleteFullName
        activityTypeIconView = activityTypeIcon
        startDateView = activityStartDate
    }

    override fun getLayoutResId() = R.layout.activity_athlete_view

    override fun setView(vararg data: Any?): BaseConstraintView {
        val athlete = data[0] as SummaryAthleteUi

        if (athlete.profile.equals(EMPTY)) {
            val drawable = AppCompatResources.getDrawable(context, R.drawable.ic_profile_medium)
            profileView.setImageDrawable(drawable)
        } else {
            //todo load from cache
        }

        fullNameView.text = athlete.fullName

        if (data.size > 1) {
            val activity = data[1] as SummaryActivityUi

            when (activity.type) {
                ActivityType.RUN.title -> activityTypeIconView.setImageResource(R.drawable.ic_run)
                ActivityType.RIDE.title -> activityTypeIconView.setImageResource(R.drawable.ic_ride)
            }

            startDateView.text = transformStartDate(activity.startDate)
        }

        return this
    }

    private fun transformStartDate(startDate: String): String {
        val formatter = SimpleDateFormat(INPUT_PATTERN, Locale.ENGLISH)
        val start = formatter.parse(startDate)

        return DateFormat.format(OUTPUT_PATTERN, start).toString()
    }

    companion object {
        const val INPUT_PATTERN = "yyyy-MM-dd'T'hh:mm:ss'Z'"
        const val OUTPUT_PATTERN = "dd MMM yy hh:mm"
    }
}
