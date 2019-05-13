package com.shvants.runninglife.json

class ActivityListGson(private val activityList: List<ActivityGson>) :
        BaseList<ActivityGson> {

    override fun getList(): List<ActivityGson> {
        return activityList
    }
}