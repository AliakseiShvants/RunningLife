package com.shvants.runninglife.ui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.UiThread
import androidx.appcompat.content.res.AppCompatResources
import com.shvants.runninglife.R
import com.shvants.runninglife.model.ui.SummaryAthleteUi
import com.shvants.runninglife.ui.view.base.BaseConstraintView
import com.shvants.runninglife.ui.view.base.BaseCustomView
import com.shvants.runninglife.utils.Const.*
import kotlinx.android.synthetic.main.activity_athlete_view.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat

class ActivityAthleteView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = NULL,
        defStyleAttr: Int = ZERO) : BaseCustomView<SummaryAthleteUi>,
        BaseConstraintView(context, attrs, defStyleAttr) {

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

    @UiThread
    override fun setView(athlete: SummaryAthleteUi?) {
        if (athlete != null) {
            if (athlete.profile == EMPTY) {
                val drawable = AppCompatResources.getDrawable(context, R.drawable.ic_profile_medium)
                profileView.setImageDrawable(drawable)
            }

            fullNameView.text = athlete.fullName
        }
    }

    fun setActivityTypeIcon(@DrawableRes resId: Int) {
        activityTypeIconView.setImageResource(resId)
    }

    fun setStartDate(startDate: String?) {
        startDateView.text = transformStartDate(startDate)
    }

    private fun transformStartDate(startDate: String?): String {
        val locale = resources.configuration.locale

//        val pattern =
//                if (locale == Locale.ENGLISH)
//                    START_DATE_OUTPUT_PATTERN_EN
//                else
//                    START_DATE_OUTPUT_PATTERN_RU

        if (startDate != null && startDate.isNotEmpty()) {
            val formatter = SimpleDateFormat(START_DATE_INPUT_PATTERN, locale)
            val start = formatter.parse(startDate)

            return DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(start)
        }

        return ""
    }

    companion object {
        private const val START_DATE_INPUT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        private const val START_DATE_OUTPUT_PATTERN_EN = "dd MMM yy hh:mm aa"
        private const val START_DATE_OUTPUT_PATTERN_RU = "dd MMM yy HH:mm"
    }
}
