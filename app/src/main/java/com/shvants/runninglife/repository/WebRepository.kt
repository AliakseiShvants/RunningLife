package com.shvants.runninglife.repository

import com.google.gson.Gson
import com.shvants.runninglife.model.gson.DetailedActivityGson
import com.shvants.runninglife.model.gson.SummaryActivityGson
import com.shvants.runninglife.model.gson.SummaryAthleteGson
import com.shvants.runninglife.model.gson.SummaryClubGson
import com.shvants.runninglife.model.gson.list.ActivityListGson
import com.shvants.runninglife.model.gson.list.AthleteListGson
import com.shvants.runninglife.model.gson.list.ClubListGson
import com.shvants.runninglife.strava.StravaRequest

class WebRepository {

    fun getAthleteActivities(token: String, page: Int): List<SummaryActivityGson> {
        val json = StravaRequest().makeAthleteActivitiesRequest(token, page)
        val activitiesArr = Gson().fromJson(json, Array<SummaryActivityGson>::class.java)

        return ActivityListGson(activitiesArr.toList()).getList()
    }

//    fun getLoggedInAthlete(): SummaryAthleteGson {
//    }

    fun getKudoers(token: String, id: Long): List<SummaryAthleteGson> {
        val json = StravaRequest().makeKudoersRequest(token, id)
        val kudoersArr = Gson().fromJson(json, Array<SummaryAthleteGson>::class.java)

        return AthleteListGson(kudoersArr.toList()).getList()
    }

    fun getActivity(token: String, id: Long): DetailedActivityGson {
        val json = StravaRequest().makeAthleteActivityRequest(token, id)

        return Gson().fromJson(json, DetailedActivityGson::class.java)
    }

    fun getClubs(token: String): List<SummaryClubGson> {
        val json = StravaRequest().makeAthleteClubsRequest(token)

        val clubsArr = Gson().fromJson(json, Array<SummaryClubGson>::class.java)

        return ClubListGson(clubsArr.toList()).getList()
    }
}