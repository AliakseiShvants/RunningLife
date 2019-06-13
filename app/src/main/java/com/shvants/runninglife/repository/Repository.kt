package com.shvants.runninglife.repository

import android.content.ContentValues
import android.content.Context
import com.shvants.runninglife.database.Contract
import com.shvants.runninglife.model.ui.DetailedActivityUi
import com.shvants.runninglife.model.ui.SummaryActivityUi
import com.shvants.runninglife.model.ui.SummaryAthleteUi
import com.shvants.runninglife.strava.StravaPreferences
import com.shvants.runninglife.utils.Converter

class Repository(context: Context) {

    private val dbRepo = DbRepository(context)
    private val webRepo = WebRepository()
    private val preferences = StravaPreferences(context)


    //detailedAthlete
//    fun getAthlete(): MetaAthlete {
//        return dbRepo.getAthlete(id) ?: webRepo.getAthlete(id)
//    }
    fun getLoggedInAthlete(): SummaryAthleteUi {
        val athleteDb = dbRepo.getLoggedInAthlete(preferences.athleteId)

        return if (athleteDb != null) {
            Converter.convertAthleteFromDbToUi(athleteDb)
        } else {
            Converter.convertAthleteFromGsonToUi(webRepo.getLoggedInAthlete())
        }
    }

    fun getAthleteActivities(page: Int): List<SummaryActivityUi> {
//        val dbList = dbRepo.getAthleteActivities(preferences.athleteId)
//
//        return if (dbList == null) {
//            val webList = webRepo.getAthleteActivities(preferences.accessToken)
//            Converter.convertActivitiesFromGsonToUi(webList)
//        } else {
//            Converter.convertActivitiesFromDbToUi(dbList)
//        }

        val webList = webRepo.getAthleteActivities(preferences.accessToken, page)

        return Converter.convertActivitiesFromGsonToUi(webList)
    }

    fun setLoggedInAthlete(): Long {

        val contentValues = ContentValues().apply {
            put(Contract.ID, preferences.athleteId)
            put(Contract.FULLNAME, preferences.fullName)
            put(Contract.PROFILE, preferences.profile)
            put(Contract.PROFILE_MEDIUM, preferences.profileMedium)
            put(Contract.AthleteEntry.LOCATION, preferences.location)
            put(Contract.SEX, preferences.sex)
            put(Contract.SUMMIT, if (preferences.summit) 1 else 0)
        }

        return dbRepo.setLoggedInAthlete(contentValues)
    }

    fun getKudoers(id: Long): List<SummaryAthleteUi> {
        val webKudoers = webRepo.getKudoers(preferences.accessToken, id)

        return Converter.convertAthleteListFromGsonToUi(webKudoers)
    }

    fun getActivity(id: Long): DetailedActivityUi {
        val activityGson = webRepo.getActivity(preferences.accessToken, id)

        return Converter.convertDetailedActivityFromGsonToUi(activityGson)
    }
}