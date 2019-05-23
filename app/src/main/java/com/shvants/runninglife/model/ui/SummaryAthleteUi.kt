package com.shvants.runninglife.model.ui

import com.shvants.runninglife.model.base.MetaAthlete

data class SummaryAthleteUi(/*val id: Int?,*/
        var profile: String,
        var fullName: String,
        var location: String) : MetaAthlete {

    class Builder(
            /*var id: Int? = null,*/
            var profile: String = "",
            var fullName: String = "",
            var location: String = "") {

        /*fun id(id: Int?) = apply { this.id = id }*/
        fun profile(profile: String) = apply { this.profile = profile }

        fun fullName(fullName: String) = apply { this.fullName = fullName }
        fun location(location: String) = apply { this.location = location }
        fun build() = SummaryAthleteUi(/*id,*/ profile, fullName, location)
    }
}