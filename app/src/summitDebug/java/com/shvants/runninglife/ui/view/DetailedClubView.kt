package com.shvants.runninglife.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
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

    override fun getLayoutResId() = R.layout.layout_detailed_club

    @UiThread
    override fun setView(club: DetailedClubUi) {
        clubNameTextView.text = club.name

        val memberTitle: String

        when (club.sportType) {
            "running" -> {
                clubSportTypeImageView.setImageResource(R.drawable.ic_run)
                memberTitle = resources.getString(R.string.runners)
            }
            "cycling" -> {
                clubSportTypeImageView.setImageResource(R.drawable.ic_ride)
                memberTitle = resources.getString(R.string.cyclers)
            }
            else -> {
                clubSportTypeImageView.visibility = View.GONE
                firstDot.visibility = View.GONE
                memberTitle = resources.getString(R.string.sportsmens)
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
        clubLocationTextView.text = location.trimEnd().removeSuffix(COMMA)

        clubStatusTextView.text = if (club.isPrivate) {
            resources.getString(R.string.privateStatus)
        } else {
            resources.getString(R.string.publicStatus)
        }

        clubDescriptionTextView.text =
                if (club.description == EMPTY) {
                    resources.getString(R.string.no_desc)
                } else {
                    club.description
                }

        clubMembersData.setView(arrayOf(club.memberCount.toString(), memberTitle))
    }
}