package com.shvants.runninglife.repository

import com.google.gson.Gson
import com.shvants.runninglife.model.gson.*
import com.shvants.runninglife.model.gson.list.ActivityListGson
import com.shvants.runninglife.model.gson.list.AthleteListGson
import com.shvants.runninglife.model.gson.list.ClubListGson
import com.shvants.runninglife.strava.StravaRequest

class WebRepository {

    fun getAthleteActivities(token: String, page: Int): List<SummaryActivityGson> {
        val json = StravaRequest().getAthleteActivities(token, page)
        val activitiesArr = Gson().fromJson(json, Array<SummaryActivityGson>::class.java)

        return ActivityListGson(activitiesArr.toList()).getList()
    }

//    fun getLoggedInAthlete(): SummaryAthleteGson {
//    }

    fun getKudoers(token: String, id: Long): List<SummaryAthleteGson> {
        val json = StravaRequest().getAthleteKudoers(token, id)
        val kudoersArr = Gson().fromJson(json, Array<SummaryAthleteGson>::class.java)

        return AthleteListGson(kudoersArr.toList()).getList()
    }

    fun getActivity(token: String, id: Long): DetailedActivityGson {
        val json = StravaRequest().getAthleteActivity(token, id)

        return Gson().fromJson(json, DetailedActivityGson::class.java)
    }

    fun getClubs(token: String): List<SummaryClubGson> {
        val json = StravaRequest().getAthleteClubs(token)

        val clubsArr = Gson().fromJson(json, Array<SummaryClubGson>::class.java)

        return ClubListGson(clubsArr.toList()).getList()
    }

    fun getClub(token: String, id: Int): DetailedClubGson {
        val json = StravaRequest().getClubRequest(token, id)
        val club = Gson().fromJson(json, DetailedClubGson::class.java)

        return club
    }

    fun deleteActivity(token: String, id: Long): Boolean {
        return StravaRequest().deleteActivityRequest(token, id)
    }
}