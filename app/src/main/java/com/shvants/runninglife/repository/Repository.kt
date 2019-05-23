package com.shvants.runninglife.repository

import android.content.ContentValues
import android.content.Context
import com.shvants.runninglife.model.ui.SummaryActivityUi
import com.shvants.runninglife.model.ui.SummaryAthleteUi
import com.shvants.runninglife.strava.StravaPreferences
import com.shvants.runninglife.utils.Converter

class Repository(context: Context?) {

    private val dbRepo = DbRepository(context)
    private val webRepo = WebRepository()
    private val preferences: StravaPreferences? = context?.let { StravaPreferences(it) }


    //detailedAthlete
//    fun getAthlete(): MetaAthlete {
//        return dbRepo.getAthlete(id) ?: webRepo.getAthlete(id)
//    }
    fun getLoggedInAthlete(): SummaryAthleteUi {
        val athleteDb = dbRepo.getLoggedInAthlete(preferences?.athleteId ?: 0L)

        return Converter.convertAthleteFromDbToUi(athleteDb)
    }

    fun getAthleteActivities(): List<SummaryActivityUi>? {
        val dbList = dbRepo.getAthleteActivities(preferences?.athleteId ?: 0L)

        return if (dbList == null) {
            val webList = webRepo.getAthleteActivities(preferences?.accessToken ?: "")
            Converter.convertActivitiesFromGsonToUi(webList)
        } else {
            Converter.convertActivitiesFromDbToUi(dbList)
        }
    }

    fun setLoggedInAthlete(contentValues: ContentValues): Long {
        return dbRepo.setLoggedInAthlete(contentValues)
    }


}