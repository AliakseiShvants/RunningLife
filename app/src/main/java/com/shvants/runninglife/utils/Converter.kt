package com.shvants.runninglife.utils

import com.shvants.runninglife.model.database.SummaryAthleteModel
import com.shvants.runninglife.model.gson.ActivityType
import com.shvants.runninglife.model.gson.SummaryActivityGson
import com.shvants.runninglife.model.gson.SummaryAthleteGson
import com.shvants.runninglife.model.ui.SummaryActivityUi
import com.shvants.runninglife.model.ui.SummaryAthleteUi
import com.shvants.runninglife.utils.Const.*

object Converter {

    private const val ZERO_DOUBLE = 0.0
    private const val DISTANCE_FORMAT = "%.2f"
    private const val H = "h"
    private const val MIN = "min"
    private const val SEC = "sec"
    private const val KM = "km"
    private const val HYPHEN = "-"
    private const val TEN = 10
    private const val ONE_MINUTE = 60
    private const val ONE_HOUR = 3600
    private const val ONE_K = 1000

    fun convertAthleteFromDbToUi(athleteDb: SummaryAthleteModel?): SummaryAthleteUi {
        return SummaryAthleteUi(id = athleteDb?._id ?: 0L,
                profile = athleteDb?.profile ?: "",
                profileMedium = athleteDb?.profile_medium ?: "",
                fullName = athleteDb?.fullname ?: "",
                location = athleteDb?.location ?: "")
    }

    fun convertAthleteFromGsonToUi(athleteGson: SummaryAthleteGson): SummaryAthleteUi {
        val fullName = "${athleteGson.firstName} ${athleteGson.lastName}"
        val location = "${athleteGson.city}$COMMA ${athleteGson.state}$COMMA ${athleteGson.country}"

        return SummaryAthleteUi(id = athleteGson.id ?: 0L,
                profile = athleteGson.profile ?: "",
                profileMedium = athleteGson.profileMedium ?: "",
                fullName = fullName,
                location = location)
    }

    fun convertAthleteListFromGsonToUi(list: List<SummaryAthleteGson>): List<SummaryAthleteUi> {
        return list.map { convertAthleteFromGsonToUi(it) }
    }

//    fun convertActivitiesFromDbToUi(list: List<SummaryActivityDb>): List<SummaryActivityUi> {
//        val result: ArrayList<SummaryActivityUi> = ArrayList()
//
//        for (activityDb in list) {
//            val distance = convertDistanceToString(activityDb.DISTANCE.toDouble())
//            val avgSpeed = when (activityDb.TYPE) {
//                ActivityType.RIDE.name -> convertAvgSpeedToString(activityDb.AVG_SPEED.toDouble())
//                else -> convertAvgTempoToString(activityDb.AVG_SPEED.toDouble())
//            }
//            val movingTime = convertMovingTimeToString(activityDb.MOVING_TIME.toInt())
//
//            result.add(SummaryActivityUi(
//                    id = activityDb.ID.toLong(),
//                    name = activityDb.NAME,
//                    movingTime = movingTime,
//                    type = activityDb.TYPE,
//                    startDate = activityDb.START_DATE,
//                    distance = distance,
//                    avgSpeed = avgSpeed
//            )
//        }
//
//        return result
//    }

    fun convertActivitiesFromGsonToUi(list: List<SummaryActivityGson>): List<SummaryActivityUi> {
        val result: ArrayList<SummaryActivityUi> = ArrayList()

        for (activityGson in list) {
            val startDate = activityGson.startDate ?: ""
            val distance = convertDistanceToString(activityGson.distance?.toDouble())
            val avgSpeed = when (activityGson.type) {
                ActivityType.RIDE.name -> convertAvgSpeedToString(activityGson.avgSpeed?.toDouble())
                else -> convertAvgTempoToString(activityGson.avgSpeed?.toDouble())
            }
            val movingTime = convertMovingTimeToString(activityGson.movingTime)

            result.add(SummaryActivityUi(
                    id = activityGson.id ?: 0L,
                    name = activityGson.name ?: "",
                    movingTime = movingTime,
                    type = activityGson.type ?: "",
                    startDate = startDate,
                    distance = distance,
                    avgSpeed = avgSpeed,
                    kudosCount = activityGson.kudosCount ?: 0,
                    startLatlng = activityGson.startLatlng ?: FloatArray(0),
                    endLatlng = activityGson.endLatlng ?: FloatArray(0),
                    map = activityGson.map?.summaryPolyline ?: ""
            ))
        }

        return result
    }

    private fun convertDistanceToString(distance: Double?): String {
        return if (distance == ZERO_DOUBLE) {
            "$HYPHEN $KM"
        } else {
            val distanceKm = distance?.div(ONE_K)
            val formatDistance = DISTANCE_FORMAT.format(distanceKm)

            "$formatDistance $KM"
        }
    }

    private fun convertMovingTimeToString(time: Int?): String {

        return if (time != null) {
            val hours = time / ONE_HOUR
            val minutes = (time - hours * ONE_HOUR) / ONE_MINUTE
            val seconds = time % ONE_MINUTE

            var hString = "$hours $H$DOT"
            var mString = "$minutes $MIN"
            var sString = "$seconds $SEC$DOT"

            when {
                hours == ZERO -> hString = EMPTY
                minutes == ZERO -> mString = EMPTY
                seconds == ZERO -> sString = EMPTY
            }

            if (hString != EMPTY)
                "$hString $mString"
            else
                "$mString $sString"
        } else {
            HYPHEN
        }
    }

    private fun convertAvgTempoToString(avgSpeed: Double?): String {

        val avgTempoLine: String

        avgTempoLine = if (avgSpeed != null && avgSpeed != ZERO_DOUBLE) {

            val tempoInSeconds = (ONE_K / avgSpeed).toInt()
            val seconds = tempoInSeconds % ONE_MINUTE
            val minutes = tempoInSeconds / ONE_MINUTE

            val mString = if (minutes != ZERO) "$minutes" else EMPTY
            val sString = if (seconds < TEN) "$ZERO$seconds" else "$seconds"

            "$mString$COLON$sString $SLASH$KM"
        } else {
            "$HYPHEN$COLON$HYPHEN $SLASH$KM"
        }

        return avgTempoLine
    }

    private fun convertAvgSpeedToString(avgSpeed: Double?): String {
        val value = if (avgSpeed != null) ONE_HOUR * avgSpeed / ONE_K else HYPHEN

        return "$value $KM$SLASH$H"
    }
}