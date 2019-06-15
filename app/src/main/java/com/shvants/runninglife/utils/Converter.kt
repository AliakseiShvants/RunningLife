package com.shvants.runninglife.utils

import com.shvants.runninglife.model.database.SummaryAthleteModel
import com.shvants.runninglife.model.gson.*
import com.shvants.runninglife.model.ui.*
import com.shvants.runninglife.utils.Const.COLON
import com.shvants.runninglife.utils.Const.COMMA
import com.shvants.runninglife.utils.Const.DOT
import com.shvants.runninglife.utils.Const.EMPTY
import com.shvants.runninglife.utils.Const.SLASH
import com.shvants.runninglife.utils.Const.ZERO
import com.shvants.runninglife.utils.Const.ZERO_LONG

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
        return SummaryAthleteUi(id = athleteDb?._id ?: ZERO_LONG,
                profile = athleteDb?.profile ?: EMPTY,
                profileMedium = athleteDb?.profile_medium ?: EMPTY,
                fullName = athleteDb?.fullname ?: EMPTY,
                location = athleteDb?.location ?: EMPTY)
    }

    fun convertAthleteFromGsonToUi(athleteGson: SummaryAthleteGson): SummaryAthleteUi {
        val fullName = "${athleteGson.firstname} ${athleteGson.lastname}"
        val location = "${athleteGson.city}$COMMA ${athleteGson.state}$COMMA ${athleteGson.country}"

        return SummaryAthleteUi(id = athleteGson.id ?: ZERO_LONG,
                profile = athleteGson.profile ?: EMPTY,
                profileMedium = athleteGson.profileMedium ?: EMPTY,
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
//            val averageSpeed = when (activityDb.TYPE) {
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
//                    startDateLocal = activityDb.START_DATE,
//                    distance = distance,
//                    averageSpeed = averageSpeed
//            )
//        }
//
//        return result
//    }

    fun convertDetailedActivityFromGsonToUi(activityGson: DetailedActivityGson): DetailedActivityUi {
        val startDate = activityGson.startDateLocal ?: EMPTY
        val distance = convertDistanceToString(activityGson.distance?.toDouble())
        val avgSpeed = when (activityGson.type) {
            ActivityType.RIDE.name -> convertAvgSpeedToString(activityGson.averageSpeed?.toDouble())
            else -> convertAvgTempoToString(activityGson.averageSpeed?.toDouble())
        }
        val movingTime = convertMovingTimeToString(activityGson.movingTime)

        return DetailedActivityUi(
                id = activityGson.id ?: ZERO_LONG,
                name = activityGson.name ?: EMPTY,
                movingTime = movingTime,
                type = activityGson.type ?: EMPTY,
                startDate = startDate,
                distance = distance,
                avgSpeed = avgSpeed,
                kudosCount = activityGson.kudosCount ?: ZERO,
                startLatlng = activityGson.startLatlng ?: FloatArray(ZERO),
                endLatlng = activityGson.endLatlng ?: FloatArray(ZERO),
                map = activityGson.map?.polyline ?: EMPTY,
                elevation = activityGson.totalElevationGain?.toInt() ?: ZERO,
                calories = activityGson.calories?.toInt() ?: ZERO,
                avgHR = activityGson.averageHeartrate?.toInt() ?: ZERO
        )
    }

    fun convertActivityFromGsonToUi(activityGson: SummaryActivityGson): SummaryActivityUi {
        val startDate = activityGson.startDateLocal ?: EMPTY
        val distance = convertDistanceToString(activityGson.distance?.toDouble())
        val avgSpeed = when (activityGson.type) {
            ActivityType.RIDE.name -> convertAvgSpeedToString(activityGson.averageSpeed?.toDouble())
            else -> convertAvgTempoToString(activityGson.averageSpeed?.toDouble())
        }
        val movingTime = convertMovingTimeToString(activityGson.movingTime)

        return SummaryActivityUi(
                id = activityGson.id ?: ZERO_LONG,
                name = activityGson.name ?: EMPTY,
                movingTime = movingTime,
                type = activityGson.type ?: EMPTY,
                startDate = startDate,
                distance = distance,
                avgSpeed = avgSpeed,
                kudosCount = activityGson.kudosCount ?: ZERO,
                startLatlng = activityGson.startLatlng ?: FloatArray(ZERO),
                endLatlng = activityGson.endLatlng ?: FloatArray(ZERO),
                map = activityGson.map?.summaryPolyline ?: EMPTY)
    }

    fun convertActivitiesFromGsonToUi(activitiesGson: List<SummaryActivityGson>): List<SummaryActivityUi> {
        return activitiesGson.map { convertActivityFromGsonToUi(it) }
    }


    fun convertClubFromGsonToUi(clubGson: SummaryClubGson): SummaryClubUi {
        return SummaryClubUi(
                id = clubGson.id ?: ZERO,
                name = clubGson.name ?: EMPTY,
                profileMedium = clubGson.profileMedium ?: EMPTY
        )
    }

    fun convertDetailedClubFromGsonToUi(clubGson: DetailedClubGson): DetailedClubUi {
        return DetailedClubUi(
                id = clubGson.id ?: ZERO,
                name = clubGson.name ?: EMPTY,
                coverPhoto = clubGson.coverPhoto ?: EMPTY,
                profileMedium = clubGson.profileMedium ?: EMPTY,
                sportType = clubGson.sportType ?: EMPTY,
                description = clubGson.description ?: EMPTY,
                city = clubGson.city ?: EMPTY,
                country = clubGson.country ?: EMPTY,
                state = clubGson.state ?: EMPTY,
                isPrivate = clubGson.isPrivate == false,
                memberCount = clubGson.memberCount ?: ZERO
        )
    }

    fun convertClubFromUiToGson(clubUi: SummaryClubUi): SummaryClubGson {
        return SummaryClubGson(
                id = clubUi.id,
                name = clubUi.name,
                profileMedium = clubUi.profileMedium
        )
    }

    fun convertClubsFromGsonToUi(clubsGson: List<SummaryClubGson>): List<SummaryClubUi> {
        return clubsGson.map { convertClubFromGsonToUi(it) }
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