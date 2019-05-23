package com.shvants.runninglife

import android.app.Application
import com.shvants.runninglife.database.DbHelper

class RunningLifeApp : Application() {

    override fun onCreate() {
        super.onCreate()

        DbHelper(applicationContext)
    }
}