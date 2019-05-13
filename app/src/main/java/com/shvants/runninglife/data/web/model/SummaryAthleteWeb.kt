package com.shvants.runninglife.data.web.model

import com.shvants.runninglife.data.base.MetaAthlete

@Deprecated("replace -Gson")
class SummaryAthleteWeb(
        override var id: Int?,
        val firstName: String?,
        val lastName: String?,
        val profileMedium: String?,
        val profile: String?,
        val city: String?,
        val state: String?,
        val country: String?,
        val sex: String?,
        val summit: Boolean?) : MetaAthlete() {

    data class Builder(
            var id: Int? = null,
            var firstName: String? = null,
            var lastName: String? = null,
            var profileMedium: String? = null,
            var profile: String? = null,
            var city: String? = null,
            var state: String? = null,
            var country: String? = null,
            var sex: String? = null,
            var summit: Boolean? = null) {

        fun id(id: Int) = apply { this.id = id }
        fun firstName(firstName: String) = apply { this.firstName = firstName }
        fun lastName(lastName: String) = apply { this.lastName = lastName }
        fun profileMedium(profileMedium: String) = apply { this.profileMedium = profileMedium }
        fun profile(profile: String) = apply { this.profile = profile }
        fun city(city: String) = apply { this.city = city }
        fun state(state: String) = apply { this.state = state }
        fun country(country: String) = apply { this.country = country }
        fun sex(sex: String) = apply { this.sex = sex }
        fun summit(summit: Boolean) = apply { this.summit = summit }
        fun build() = SummaryAthleteWeb(id, firstName, lastName, profileMedium, profile, city,
                state, country, sex, summit)
    }
}