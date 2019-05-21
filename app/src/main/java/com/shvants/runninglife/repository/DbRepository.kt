package com.shvants.runninglife.repository

import com.shvants.runninglife.model.db.SummaryActivityDb
import com.shvants.runninglife.model.db.SummaryAthleteDb

class DbRepository {

    private lateinit var athlete: SummaryAthleteDb

    fun getLoggedInAthlete(id: Int): SummaryAthleteDb? {
        athlete = SummaryAthleteDb("1", "Aliaksei Shvants", "", "",
                "Grodno", "M", "false")

        return athlete
    }

    fun getAthleteActivities(id: Int): List<SummaryActivityDb> {
        val list = ArrayList<SummaryActivityDb>()
        list.add(SummaryActivityDb("1", "1", "Morning Run", "3663",
                "Run", "2019-05-07T12:12:12Z", "12.32", "3.36",
                "google_disk"))

        return list
    }

}