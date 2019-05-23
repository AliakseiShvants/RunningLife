package com.shvants.runninglife.mvp.presenter

import android.content.ContentValues
import android.content.Context
import com.shvants.runninglife.database.Contract
import com.shvants.runninglife.repository.Repository
import com.shvants.runninglife.strava.StravaPreferences

class LoginPresenter(private var context: Context?) : BasePresenter {

    private lateinit var repository: Repository

    override fun onCreate() {
        repository = Repository(context)
    }

    fun setLoggedInAthlete(preferences: StravaPreferences): Long {
        val contentValues = ContentValues().apply {
            put(Contract.ID, preferences.athleteId)
            put(Contract.FULLNAME, preferences.fullName)
            put(Contract.PROFILE, preferences.profile)
            put(Contract.PROFILE_MEDIUM, preferences.profileMedium)
            put(Contract.AthleteEntry.LOCATION, preferences.location)
            put(Contract.SEX, preferences.sex)
            put(Contract.SUMMIT, if (preferences.summit) 1 else 0)
        }

        return repository.setLoggedInAthlete(contentValues)
    }

    override fun onDestroy() {
        context = null
    }


}