package com.shvants.runninglife.loaders

import android.content.Context
import androidx.loader.content.AsyncTaskLoader
import com.shvants.runninglife.strava.StravaHelper
import com.shvants.runninglife.strava.StravaPreferences
import com.shvants.runninglife.strava.StravaRequest

class StravaTokenLoader(context: Context) : AsyncTaskLoader<String>(context) {

    override fun loadInBackground(): String? {

        val tokenBody = StravaHelper.getTokenBody(StravaPreferences(context).code)
        val result = StravaRequest(StravaHelper.TOKEN_BASE_URL).post(tokenBody)

        return result
    }


}