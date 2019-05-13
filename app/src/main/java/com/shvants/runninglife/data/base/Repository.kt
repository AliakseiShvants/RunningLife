package com.shvants.runninglife.data.base

interface Repository {

    fun getLoggedAthlete(): MetaAthlete

    fun getAthleteSummaryActivities(id: Int?): List<MetaActivity>
}