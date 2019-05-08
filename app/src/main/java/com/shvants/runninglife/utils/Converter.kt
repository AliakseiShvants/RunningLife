package com.shvants.runninglife.utils

import android.content.Context
import com.shvants.runninglife.R
import com.shvants.runninglife.data.db.model.SummaryActivityDb
import com.shvants.runninglife.data.db.model.SummaryAthleteDb
import com.shvants.runninglife.data.web.model.ActivityType
import com.shvants.runninglife.ui.model.SummaryActivityUi
import com.shvants.runninglife.ui.model.SummaryAthleteUi
import com.shvants.runninglife.utils.Const.*

object Converter {

    private const val ZERO_DOUBLE = 0.0
    private const val DISTANCE_FORMAT = "%.2f"
    private const val ONE_MINUTE = 60
    private const val ONE_HOUR = 3600
    private const val ONE_K = 1000

    fun convertAthleteFromDbToUi(athleteDb: SummaryAthleteDb): SummaryAthleteUi {
        return SummaryAthleteUi.Builder()
                .id(athleteDb.ID.toInt())
                .profile(athleteDb.PROFILE)
                .fullName(athleteDb.FULLNAME)
                .location(athleteDb.LOCATION)
                .build()
    }

    fun convertSummaryActivityFromDbToUi(context: Context?,
                                         activityDb: SummaryActivityDb): SummaryActivityUi {

        val distance = convertDistanceToString(context, activityDb.DISTANCE.toDouble())
        val avgSpeed = when (activityDb.TYPE) {
            ActivityType.RIDE.name -> convertAvgSpeedToString(context, activityDb.AVG_SPEED.toDouble())
            else -> convertAvgTempoToString(context, activityDb.AVG_SPEED.toDouble())
        }
        val movingTime = convertMovingTimeToString(context, activityDb.MOVING_TIME.toInt())

        return SummaryActivityUi.Builder()
                .id(activityDb.ID.toInt())
                .name(activityDb.NAME)
                .movingTime(movingTime)
                .type(activityDb.TYPE)
                .startDate(activityDb.START_DATE)
                .distance(distance).avgSpeed(avgSpeed)
                .map(activityDb.MAP)
                .build()
    }

    private fun convertDistanceToString(context: Context?, distance: Double?): String {
        return if (distance == ZERO_DOUBLE) {
            "$HYPHEN ${context?.resources?.getString(R.string.km)}"
        } else {
            val formatDistance = DISTANCE_FORMAT.format(distance)

            "$formatDistance ${context?.resources?.getString(R.string.km)}"
        }
    }

    private fun convertMovingTimeToString(context: Context?, time: Int): String {

        val result: String
        val hours = time / ONE_HOUR
        val minutes = (time - hours * ONE_HOUR) / ONE_MINUTE
        val seconds = time % ONE_MINUTE

        var hString = "$hours ${context?.resources?.getString(R.string.hours)}$DOT"
        var mString = "$minutes ${context?.resources?.getString(R.string.minutes)}"
        var sString = "$seconds ${context?.resources?.getString(R.string.seconds)}$DOT"

        when {
            hours == ZERO -> hString = EMPTY
            minutes == ZERO -> mString = EMPTY
            seconds == ZERO -> sString = EMPTY
        }

        result = if (hString != EMPTY)
            "$hString $mString"
        else
            "$mString $sString"

        return result
    }

    private fun convertAvgTempoToString(context: Context?, avgSpeed: Double): String {

        val avgTempoLine: String

        avgTempoLine = if (avgSpeed != ZERO_DOUBLE) {

            val tempoInSeconds = (ONE_K / avgSpeed).toInt()
            val seconds = tempoInSeconds % ONE_MINUTE
            val minutes = tempoInSeconds / ONE_MINUTE

            val mString = if (minutes != ZERO) "$minutes" else EMPTY
            val sString = if (seconds < TEN) "$ZERO$seconds" else "$seconds"

            "$mString$COLON$sString $SLASH${context?.resources?.getString(R.string.km)}"
        } else {
            "$HYPHEN$COLON$HYPHEN $SLASH${context?.resources?.getString(R.string.km)}"
        }

        return avgTempoLine
    }

    private fun convertAvgSpeedToString(context: Context?, avgSpeed: Double): String {
        val value = ONE_HOUR * avgSpeed / ONE_K
        val km = context?.resources?.getString(R.string.km)
        val h = context?.resources?.getString(R.string.hours)

        return "$value $km$SLASH$h"
    }
}