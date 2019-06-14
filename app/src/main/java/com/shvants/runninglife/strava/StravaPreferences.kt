package com.shvants.runninglife.strava

import android.content.Context
import android.content.SharedPreferences
import com.shvants.runninglife.database.Contract.SEX
import com.shvants.runninglife.strava.StravaHelper.ACCESS_TOKEN
import com.shvants.runninglife.strava.StravaHelper.APP_PREFERENCES
import com.shvants.runninglife.strava.StravaHelper.ATHLETE_ID
import com.shvants.runninglife.strava.StravaHelper.CODE
import com.shvants.runninglife.strava.StravaHelper.EXPIRES_AT
import com.shvants.runninglife.strava.StravaHelper.EXPIRES_IN
import com.shvants.runninglife.strava.StravaHelper.REFRESH_TOKEN
import com.shvants.runninglife.strava.StravaHelper.TOKEN_TYPE
import com.shvants.runninglife.utils.Const
import com.shvants.runninglife.utils.Const.FULLNAME
import com.shvants.runninglife.utils.Const.LOCATION
import com.shvants.runninglife.utils.Const.PROFILE
import com.shvants.runninglife.utils.Const.PROFILE_MEDIUM
import com.shvants.runninglife.utils.Const.SUMMIT
import com.shvants.runninglife.utils.Const.ZERO
import com.shvants.runninglife.utils.Const.ZERO_LONG

class StravaPreferences(context: Context) {

    private val preferences: SharedPreferences =
            context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

    private val isLogged = preferences.contains(ACCESS_TOKEN)

    var athleteId: Long
        get() = preferences.getLong(ATHLETE_ID, ZERO_LONG)
        set(value) = preferences.edit()
                .putLong(ATHLETE_ID, value)
                .apply()

    var accessToken: String
        get() = preferences.getString(ACCESS_TOKEN, Const.EMPTY)
        set(value) = preferences.edit()
                .putString(ACCESS_TOKEN, value)
                .apply()

    var code: String
        get() = preferences.getString(CODE, Const.EMPTY)
        set(value) = preferences.edit()
                .putString(CODE, value)
                .apply()

    var fullName: String
        get() = preferences.getString(FULLNAME, Const.EMPTY)
        set(value) = preferences.edit()
                .putString(FULLNAME, value)
                .apply()

    var expiresAt: Int
        get() = preferences.getInt(EXPIRES_AT, ZERO)
        set(value) = preferences.edit()
                .putInt(EXPIRES_AT, value)
                .apply()

    var expires_in: Int
        get() = preferences.getInt(EXPIRES_IN, ZERO)
        set(value) = preferences.edit()
                .putInt(EXPIRES_IN, value)
                .apply()

    var refreshToken: String
        get() = preferences.getString(REFRESH_TOKEN, Const.EMPTY)
        set(value) = preferences.edit()
                .putString(REFRESH_TOKEN, value)
                .apply()

    var location: String
        get() = preferences.getString(LOCATION, Const.EMPTY)
        set(value) = preferences.edit()
                .putString(LOCATION, value)
                .apply()

    var profile: String
        get() = preferences.getString(PROFILE, Const.EMPTY)
        set(value) = preferences.edit()
                .putString(PROFILE, value)
                .apply()

    var profileMedium: String
        get() = preferences.getString(PROFILE_MEDIUM, Const.EMPTY)
        set(value) = preferences.edit()
                .putString(PROFILE_MEDIUM, value)
                .apply()

    var sex: String
        get() = preferences.getString(SEX, Const.EMPTY)
        set(value) = preferences.edit()
                .putString(SEX, value)
                .apply()

    var summit: Boolean
        get() = preferences.getBoolean(SUMMIT, false)
        set(value) = preferences.edit()
                .putBoolean(SUMMIT, value)
                .apply()

    var tokenType: String
        get() = preferences.getString(TOKEN_TYPE, Const.EMPTY)
        set(value) = preferences.edit()
                .putString(TOKEN_TYPE, value)
                .apply()


    fun logout() {
        preferences.edit()
                .remove(ACCESS_TOKEN)
                .remove(REFRESH_TOKEN)
                .apply()
    }
}
