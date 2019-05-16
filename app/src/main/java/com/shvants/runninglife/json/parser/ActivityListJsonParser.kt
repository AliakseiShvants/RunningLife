package com.shvants.runninglife.json.parser

import com.google.gson.Gson
import com.shvants.runninglife.data.web.model.ActivityListGson
import com.shvants.runninglife.data.web.model.SummaryActivityGson

class ActivityListJsonParser(private val json: String?) : BaseListParser {

    override fun parse(): Any {
        val result = Gson().fromJson(json, Array<SummaryActivityGson>::class.java)

        return ActivityListGson(result.toList())
    }
}