package com.shvants.runninglife.data.base

import com.shvants.runninglife.data.db.model.SummaryActivityDb
import com.shvants.runninglife.data.db.model.SummaryAthleteDb

class DbRepository private constructor() : Repository {

    private lateinit var athlete: SummaryAthleteDb

    override fun getLoggedAthlete(): SummaryAthleteDb {
        athlete = SummaryAthleteDb("1", "Aliaksei Shvants", "", "",
                "Grodno", "M", "false")

        return athlete
    }

    override fun getAthleteSummaryActivities(id: Int): List<SummaryActivityDb> {
        val list = ArrayList<SummaryActivityDb>()
        list.add(SummaryActivityDb("1", "1", "Morning Run", "3663",
                "Run", "2019-05-07T12:12:12Z", "12.32", "3.36",
                "google_disk"))

        return list
    }

    companion object {
        val instance = DbRepository()
    }
}