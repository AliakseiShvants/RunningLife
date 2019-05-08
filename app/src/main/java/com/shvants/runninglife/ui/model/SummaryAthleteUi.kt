package com.shvants.runninglife.ui.model

import com.shvants.runninglife.data.base.MetaAthlete

data class SummaryAthleteUi(var id: Int?,
                            var profile: String?,
                            var fullName: String?,
                            var location: String?) : MetaAthlete {

    data class Builder(
            var id: Int? = null,
            var profile: String? = null,
            var fullName: String? = null,
            var location: String? = null) {

        fun id(id: Int) = apply { this.id = id }
        fun profile(profile: String) = apply { this.profile = profile }
        fun fullName(fullName: String) = apply { this.fullName = fullName }
        fun location(location: String) = apply { this.location = location }
        fun build() = SummaryAthleteUi(id, profile, fullName, location)
    }
}