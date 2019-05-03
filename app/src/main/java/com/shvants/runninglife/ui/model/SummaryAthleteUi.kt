package com.shvants.runninglife.ui.model

import com.shvants.runninglife.R

data class SummaryAthleteUi(val id: Int?,
                            var profile: Int? = R.drawable.ic_profile,
                            var fullName: String? = "Full name",
                            var location: String? = "Location") {

    data class Builder(
            var id: Int? = null,
            var profile: Int? = null,
            var fullName: String? = null,
            var location: String? = null) {

        fun id(id: Int) = apply { this.id = id }
        fun profile(profile: Int) = apply { this.profile = profile }
        fun fullName(fullName: String) = apply { this.fullName = fullName }
        fun location(location: String) = apply { this.location = location }
        fun build() = SummaryAthleteUi(id, profile, fullName, location)
    }
}