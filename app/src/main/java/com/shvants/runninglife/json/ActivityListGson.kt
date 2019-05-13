package com.shvants.runninglife.json

class ActivityListGson(private val activityList: List<SummaryActivityGson>) :
        BaseList<SummaryActivityGson> {

    override fun getList(): List<SummaryActivityGson> {
        return activityList
    }
}