package com.shvants.runninglife.utils

import com.shvants.runninglife.data.db.model.SummaryAthleteDb
import com.shvants.runninglife.ui.model.SummaryAthleteUi

object Converter {

    fun convertAthleteFromDbToUi(athleteDb: SummaryAthleteDb): SummaryAthleteUi {
        return SummaryAthleteUi.Builder()
                .id(athleteDb.ID.toInt())
                .profile(athleteDb.PROFILE.toInt())
                .fullName(athleteDb.FULLNAME)
                .location(athleteDb.LOCATION)
                .build()
    }
}