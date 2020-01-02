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

private const val PUBLIC = "Public"
private const val PRIVATE = "Private"
private const val NO_DESCRIPTION = "There is no description yet!"

class DetailedClubView(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = ZERO
) : BaseCustomView<DetailedClubUi>, BaseConstraintView(context, attrs, defStyleAttr) {

    override fun getLayoutResId() = R.layout.layout_detailed_club

    @UiThread
    override fun setView(club: DetailedClubUi) {
        clubName.text = club.name

        val memberTitle: String

        when (club.sportType) {
            "running" -> {
                clubSportType.setImageResource(R.drawable.ic_run)
                memberTitle = "runners"
            }
            "cycling" -> {
                clubSportType.setImageResource(R.drawable.ic_ride)
                memberTitle = "cyclers"
            }
            else -> {
                clubSportType.visibility = View.GONE
                firstDot.visibility = View.GONE
                memberTitle = "sportsmens"
            }
        }

        val locationArr = arrayOf(club.city, club.state, club.country)
        var location = EMPTY

        for (i in locationArr.indices) {
            val item = locationArr[i]

            if (item != EMPTY) location += "$item$COMMA "
        }

        clubLocation.text = location.trimEnd().removeSuffix(COMMA)
        clubStatus.text = if (club.isPrivate) PRIVATE else PUBLIC
        clubDescription.text = if (club.description == EMPTY) NO_DESCRIPTION else club.description

        clubMembers.setView(arrayOf(club.memberCount.toString(), memberTitle))
    }
}