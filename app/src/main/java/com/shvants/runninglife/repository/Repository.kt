package com.shvants.runninglife.repository

import android.content.Context
import com.shvants.runninglife.model.ui.SummaryActivityUi
import com.shvants.runninglife.model.ui.SummaryAthleteUi
import com.shvants.runninglife.strava.StravaPreferences
import com.shvants.runninglife.utils.Converter

class Repository(context: Context?) {

    private val dbRepo = DbRepository()
    private val webRepo = WebRepository()
    private val preferences: StravaPreferences? = context?.let { StravaPreferences(it) }


    //detailedAthlete
//    fun getAthlete(): MetaAthlete {
//        return dbRepo.getAthlete(id) ?: webRepo.getAthlete(id)
//    }
    fun getLoggedInAthlete(): SummaryAthleteUi {
        val athleteDb = dbRepo.getLoggedInAthlete(preferences?.athleteId ?: 0)

        return if (athleteDb != null) {
            Converter.convertAthleteFromDbToUi(athleteDb)
        } else {
            // todo ADD TO DB
            SummaryAthleteUi
                    .Builder()
                    .fullName(preferences?.fullName ?: "")
                    .profile(preferences?.profile ?: "")
                    .location(preferences?.location ?: "")
                    .build()
        }
    }

    fun getAthleteActivities(): List<SummaryActivityUi>? {
        val dbList = dbRepo.getAthleteActivities(preferences?.athleteId ?: 0)

        return if (dbList.isEmpty()) {
            val webList = webRepo.getAthleteActivities(preferences?.accessToken ?: "")
            Converter.convertActivitiesFromGsonToUi(webList)
        } else {
            Converter.convertActivitiesFromDbToUi(dbList)
        }
    }


}