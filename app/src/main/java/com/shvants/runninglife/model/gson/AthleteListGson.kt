package com.shvants.runninglife.model.gson

class AthleteListGson(private val athleteList: List<SummaryAthleteGson>)
    : BaseList<SummaryAthleteGson> {

    override fun getList() = athleteList
}