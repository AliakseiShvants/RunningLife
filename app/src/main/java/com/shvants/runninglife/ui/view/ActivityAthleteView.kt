package com.shvants.runninglife.ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import com.shvants.runninglife.R
import com.shvants.runninglife.model.ui.SummaryAthleteUi
import com.shvants.runninglife.ui.view.base.BaseConstraintView
import com.shvants.runninglife.ui.view.base.BaseCustomView
import com.shvants.runninglife.utils.Const.EMPTY
import com.shvants.runninglife.utils.Const.ZERO
import kotlinx.android.synthetic.main.activity_athlete_view.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat

private const val START_DATE_INPUT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'"

class ActivityAthleteView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = ZERO
) : BaseCustomView<SummaryAthleteUi>, BaseConstraintView(context, attrs, defStyleAttr) {


    override fun getLayoutResId() = R.layout.activity_athlete_view

    override fun setView(athlete: SummaryAthleteUi) {
        if (athlete.profile == EMPTY) {
            val drawable = AppCompatResources.getDrawable(context, R.drawable.ic_profile_medium)
            athleteProfile.setImageDrawable(drawable)
        }

        athleteFullName.text = athlete.fullName
    }

    fun setActivityTypeIcon(@DrawableRes resId: Int) {
        activityTypeIcon.setImageResource(resId)
    }

    fun setStartDate(startDate: String?) {
        activityStartDate.text = transformStartDate(startDate)
    }

    private fun transformStartDate(startDate: String?): String {
        val locale = resources.configuration.locale

        if (startDate != null && startDate.isNotEmpty()) {
            val formatter = SimpleDateFormat(START_DATE_INPUT_PATTERN, locale)
            val start = formatter.parse(startDate)

            return DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(start)
        }

        return EMPTY
    }
}
