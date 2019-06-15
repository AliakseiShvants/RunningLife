package com.shvants.runninglife.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.UiThread
import com.shvants.runninglife.R
import com.shvants.runninglife.model.ui.DetailedClubUi
import com.shvants.runninglife.ui.view.base.BaseConstraintView
import com.shvants.runninglife.ui.view.base.BaseCustomView
import com.shvants.runninglife.utils.Const.COMMA
import com.shvants.runninglife.utils.Const.EMPTY
import com.shvants.runninglife.utils.Const.ZERO
import kotlinx.android.synthetic.summitDebug.layout_detailed_club.view.*

class DetailedClubView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = ZERO) : BaseCustomView<DetailedClubUi>,
        BaseConstraintView(context, attrs, defStyleAttr) {

    private lateinit var coverFotoView: ImageView
    private lateinit var profileView: ImageView
    private lateinit var nameView: TextView
    private lateinit var sportTypeView: ImageView
    private lateinit var descriptionView: TextView
    private lateinit var locationView: TextView
    private lateinit var statusView: TextView
    private lateinit var memberView: ClubDataItemView
    private lateinit var activityView: ClubDataItemView
    private lateinit var recordView: ClubDataItemView

    override fun onViewInflated(context: Context) {
        coverFotoView = clubCoverFoto
        profileView = clubProfile
        nameView = clubName
        sportTypeView = clubSportType
        descriptionView = clubDescription
        locationView = clubLocation
        statusView = clubStatus
        memberView = clubMembers
        activityView = clubActivities
        recordView = clubRecords
    }

    override fun getLayoutResId() = R.layout.layout_detailed_club

    @UiThread
    override fun setView(club: DetailedClubUi) {
        nameView.text = club.name

        val memberTitle: String

        when (club.sportType) {
            "running" -> {
                sportTypeView.setImageResource(R.drawable.ic_run)
                memberTitle = "runners"
            }
            "cycling" -> {
                sportTypeView.setImageResource(R.drawable.ic_ride)
                memberTitle = "cyclers"
            }
            else -> {
                sportTypeView.visibility = View.GONE
                firstDot.visibility = View.GONE
                memberTitle = "sportsmens"
            }
        }

        val city = if (club.city != EMPTY) "${club.city}$COMMA" else EMPTY
        val state = if (club.state != EMPTY) "${club.state}$COMMA" else EMPTY
        val country = club.country

        if (city == EMPTY && state == EMPTY && country == EMPTY) {
            locationView.visibility = View.GONE
            secondDot.visibility = View.GONE
        } else {
            val location = "$city $state $country"
            locationView.text = location
        }

        statusView.text = if (club.isPrivate) "Opened" else "Closed"

        descriptionView.text =
                if (club.description == EMPTY) {
                    "There is no description yet!"
                } else {
                    club.description
                }

//        memberView.
        memberView.setView(arrayOf(club.memberCount.toString(), memberTitle))
//
//        athleteView.setStartDate(activity.startDate)
//
//        dataView.setView(activity)
//        nameView.text = activity.name
//        likeView.seView(activity)
    }

//    @UiThread
//    fun setAthleteView(athlete: SummaryAthleteUi) {
//        athleteView.setView(athlete)
//    }
//
//    fun getDetailedAthleteProfile() = athleteView.getProfileView()
}