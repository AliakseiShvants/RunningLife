package com.shvants.runninglife.ui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.UiThread
import com.shvants.runninglife.R
import com.shvants.runninglife.model.ui.SummaryAthleteUi
import com.shvants.runninglife.ui.view.base.BaseConstraintView
import com.shvants.runninglife.ui.view.base.BaseCustomView
import com.shvants.runninglife.utils.Const.ZERO
import kotlinx.android.synthetic.main.nav_athlete_view.view.*

class NavAthleteView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = ZERO
) : BaseConstraintView(context, attrs, defStyleAttr), BaseCustomView<SummaryAthleteUi> {

    override fun getLayoutResId() = R.layout.nav_athlete_view

    @UiThread
    override fun setView(athleteUi: SummaryAthleteUi) {
        navAthleteFullName.text = athleteUi.fullName
        navAthleteLocation.text = athleteUi.location
    }
}
