package com.shvants.runninglife.model.gson.list

import com.shvants.runninglife.model.gson.SummaryAthleteGson

class AthleteListGson(private val athleteList: List<SummaryAthleteGson>)
    : BaseList<SummaryAthleteGson> {

    override fun getList() = athleteList
}