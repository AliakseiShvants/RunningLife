package com.shvants.runninglife.data.db.model

import com.shvants.runninglife.data.base.Repository
import com.shvants.runninglife.data.base.SummaryActivity
import com.shvants.runninglife.data.base.SummaryAthlete

class DbRepository : Repository {

    override fun getLoggedAthlete(): SummaryAthlete {
        return SummaryAthleteDb("1", "Aliaksei Shvants", "", "", "Grodno", "M", "false")
    }

    override fun getAthleteActivities(): List<SummaryActivity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}