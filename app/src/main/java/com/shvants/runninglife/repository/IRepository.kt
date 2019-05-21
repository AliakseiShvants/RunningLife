package com.shvants.runninglife.repository

import com.shvants.runninglife.model.base.MetaActivity
import com.shvants.runninglife.model.base.MetaAthlete

interface IRepository {

    fun getAthlete(id: Int? = 0): MetaAthlete?

    fun getAthleteActivities(id: Int = 0, token: String = ""): List<MetaActivity>?
}