package com.shvants.runninglife.repository

import com.google.gson.Gson
import com.shvants.runninglife.model.gson.ActivityListGson
import com.shvants.runninglife.model.gson.SummaryActivityGson
import com.shvants.runninglife.strava.StravaRequest

class WebRepository {

    fun getAthleteActivities(token: String, page: Int): List<SummaryActivityGson> {
        //todo handle if token empty
        val json = StravaRequest().makeAthleteActivitiesRequest(token, page)
        val activitiesArr = Gson().fromJson(json, Array<SummaryActivityGson>::class.java)

        return ActivityListGson(activitiesArr.toList()).getList()
    }


}