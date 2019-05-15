package com.shvants.runninglife.strava

import android.content.Context
import android.content.SharedPreferences
import com.shvants.runninglife.strava.StravaHelper.ACCESS_TOKEN
import com.shvants.runninglife.strava.StravaHelper.AUTH_PREFERENCES
import com.shvants.runninglife.utils.Const

class StravaTokenizer(context: Context) {

    private val preferences: SharedPreferences =
            context.getSharedPreferences(AUTH_PREFERENCES, Context.MODE_PRIVATE)

    private val isLogged = preferences.contains(ACCESS_TOKEN)

    var accessToken: String
        get() = preferences.getString(ACCESS_TOKEN, Const.EMPTY)
        set(value) = preferences
                .edit()
                .putString(ACCESS_TOKEN, value)
                .apply()
}