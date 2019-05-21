package com.shvants.runninglife.database

import android.util.Log
import com.shvants.runninglife.model.db.SummaryActivityDb
import com.shvants.runninglife.model.db.SummaryAthleteDb

object Contract {

    private val TAG = Contract::class.simpleName

    fun getTables(): List<Class<*>> {
        Log.d(TAG, "inside getTables method")
        return listOf(SummaryAthleteDb::class.java, SummaryActivityDb::class.java)
    }
}
