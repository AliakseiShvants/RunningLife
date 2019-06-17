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

        val locationArr = arrayOf(club.city, club.state, club.country)
        var location = EMPTY

        for (i in locationArr.indices) {
            val item = locationArr[i]

            if (item != EMPTY) {
                location += "$item$COMMA "
            }
        }

        locationView.text = location.trimEnd().removeSuffix(COMMA)
//        when {
//            city == EMPTY && state == EMPTY && country == EMPTY -> {
//                locationView.visibility = View.GONE
//                secondDot.visibility = View.GONE
//            }
//            city == EMPTY -> {
//                location = "$state$country"
//                locationView.text = location
//            }
//            else -> {
//                location = "$city$state"
//                locationView.text = location
//            }
//        }

        statusView.text = if (club.isPrivate) PRIVATE else PUBLIC

        descriptionView.text =
                if (club.description == EMPTY) {
                    NO_DESCRIPTION
                } else {
                    club.description
                }

        memberView.setView(arrayOf(club.memberCount.toString(), memberTitle))
    }

    companion object {
        private const val PUBLIC = "Public"
        private const val PRIVATE = "Private"
        private const val NO_DESCRIPTION = "There is no description yet!"
    }
}