package com.shvants.runninglife.model.gson.list

import com.shvants.runninglife.model.gson.SummaryActivityGson

class ActivityListGson(private val activityList: List<SummaryActivityGson>) :
        BaseList<SummaryActivityGson> {

    override fun getList() = activityList
}