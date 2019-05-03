package com.shvants.runninglife.data.base

interface Repository {

    fun getLoggedAthlete(): SummaryAthlete

    fun getAthleteActivities(): List<SummaryActivity>
}