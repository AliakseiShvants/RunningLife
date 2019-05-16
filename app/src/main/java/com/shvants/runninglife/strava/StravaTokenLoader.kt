package com.shvants.runninglife.strava

import android.content.Context
import androidx.loader.content.AsyncTaskLoader

class StravaTokenLoader(context: Context) : AsyncTaskLoader<String>(context) {

    override fun loadInBackground(): String? {

        val tokenBody = StravaHelper.getTokenBody(StravaPreferences(context).code)
        val result = StravaRequest(StravaHelper.TOKEN_BASE_URL).post(tokenBody)

        return result
    }

}