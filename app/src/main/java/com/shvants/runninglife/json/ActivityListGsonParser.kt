package com.shvants.runninglife.json

import com.google.gson.Gson

class ActivityListGsonParser(private val json: String) : BaseListParser {

    override fun parse(): Any {
        val result = Gson().fromJson(json, Array<ActivityGson>::class.java)

        return ActivityListGson(result.toList())
    }
}