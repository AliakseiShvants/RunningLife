package com.shvants.runninglife.model.ui

import com.shvants.runninglife.model.base.MetaAthlete

data class SummaryAthleteUi(
        var id: Long,
        var profile: String,
        var profileMedium: String,
        var fullName: String,
        var location: String) : MetaAthlete