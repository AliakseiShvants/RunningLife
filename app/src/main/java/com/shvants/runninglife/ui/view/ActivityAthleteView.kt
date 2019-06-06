package com.shvants.runninglife.ui.view

import android.content.Context
import android.text.format.DateFormat
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
import java.text.SimpleDateFormat
import java.util.*

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
    override fun setView(athlete: SummaryAthleteUi) {

        if (athlete.profile == EMPTY) {
            val drawable = AppCompatResources.getDrawable(context, R.drawable.ic_profile_medium)
            profileView.setImageDrawable(drawable)
        }

        fullNameView.text = athlete.fullName
    }

    fun setActivityTypeIcon(@DrawableRes resId: Int) {
        activityTypeIconView.setImageResource(resId)
    }

    fun setStartDate(startDate: String) {
        startDateView.text = transformStartDate(startDate)
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
