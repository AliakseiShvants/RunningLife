package com.shvants.runninglife.model.gson

class ActivityListGson(private val activityList: List<SummaryActivityGson>) :
        BaseList<SummaryActivityGson> {

    override fun getList() = activityList
}