package com.shvants.runninglife.data.db

import android.util.Log
import com.shvants.runninglife.data.db.model.SummaryActivityDb
import com.shvants.runninglife.data.db.model.SummaryAthleteDb

object Contract {

    private val TAG = Contract::class.simpleName

    fun getTables(): List<Class<*>> {
        Log.d(TAG, "inside getTables method")
        return listOf(SummaryAthleteDb::class.java, SummaryActivityDb::class.java)
    }
}
