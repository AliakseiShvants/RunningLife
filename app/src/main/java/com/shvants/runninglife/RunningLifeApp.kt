package com.shvants.runninglife

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.shvants.runninglife.database.DbHelper

class RunningLifeApp : Application() {

    override fun onCreate() {
        super.onCreate()

        DbHelper(applicationContext)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }
}