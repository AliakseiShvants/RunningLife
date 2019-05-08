package com.shvants.runninglife.data.base

import com.shvants.runninglife.data.db.model.SummaryActivityDb

interface Repository {

    fun getLoggedAthlete(): MetaAthlete

    fun getAthleteSummaryActivities(id: Int): List<SummaryActivityDb>
}