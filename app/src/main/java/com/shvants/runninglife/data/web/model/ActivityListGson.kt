package com.shvants.runninglife.data.web.model

class ActivityListGson(private val activityList: List<SummaryActivityGson>) :
        BaseList<SummaryActivityGson> {

    override fun getList(): List<SummaryActivityGson> {
        return activityList
    }
}