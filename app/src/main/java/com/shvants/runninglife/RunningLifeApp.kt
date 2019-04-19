package com.shvants.runninglife

import android.app.Application
import com.shvants.runninglife.database.DbHelper
import com.shvants.runninglife.utils.Const.Database.DATABASE_VERSION

class RunningLifeApp : Application() {

    override fun onCreate() {
        super.onCreate()

        DbHelper(applicationContext, null, DATABASE_VERSION)
    }

}