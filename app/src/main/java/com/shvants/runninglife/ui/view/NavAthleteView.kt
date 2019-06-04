package com.shvants.runninglife.ui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import com.shvants.runninglife.R
import com.shvants.runninglife.model.ui.SummaryAthleteUi
import com.shvants.runninglife.ui.view.base.BaseConstraintView
import com.shvants.runninglife.ui.view.base.BaseCustomView
import com.shvants.runninglife.utils.Const
import com.shvants.runninglife.utils.Const.NULL
import com.shvants.runninglife.utils.Const.ZERO
import kotlinx.android.synthetic.main.nav_athlete_view.view.*

class NavAthleteView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = NULL,
        defStyleAttr: Int = ZERO) : BaseConstraintView(context, attrs, defStyleAttr),
        BaseCustomView<SummaryAthleteUi> {

    private lateinit var profileView: ImageView
    private lateinit var fullNameView: TextView
    private lateinit var locationView: TextView

    override fun onViewInflated(context: Context) {
        profileView = navAthleteProfile
        fullNameView = navAthleteFullName
        locationView = navAthleteLocation
    }

    override fun getLayoutResId() = R.layout.nav_athlete_view

    override fun setView(athleteUi: SummaryAthleteUi) {

        if (athleteUi.profile == Const.EMPTY) {
            profileView.setImageResource(R.drawable.ic_profile)
        } else {
            //todo load from cache
        }

        fullNameView.text = athleteUi.fullName
        locationView.text = athleteUi.location
    }
}