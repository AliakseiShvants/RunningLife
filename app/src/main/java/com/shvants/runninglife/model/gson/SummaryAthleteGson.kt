package com.shvants.runninglife.model.gson

import com.google.gson.annotations.SerializedName
import com.shvants.runninglife.model.base.MetaAthlete

data class SummaryAthleteGson(
        @SerializedName("id") val id: Long?,
        @SerializedName("firstname") val firstName: String?,
        @SerializedName("lastname") val lastName: String?,
        @SerializedName("profile_medium") val profileMedium: String?,
        @SerializedName("profile") val profile: String?,
        @SerializedName("city") val city: String?,
        @SerializedName("state") val state: String?,
        @SerializedName("country") val country: String?,
        @SerializedName("sex") val sex: String?,
        @SerializedName("summit") val summit: Boolean?) : MetaAthlete {

    data class Builder(
            var id: Long? = null,
            var firstName: String? = null,
            var lastName: String? = null,
            var profileMedium: String? = null,
            var profile: String? = null,
            var city: String? = null,
            var state: String? = null,
            var country: String? = null,
            var sex: String? = null,
            var summit: Boolean? = null) {

        fun id(id: Long) = apply { this.id = id }
        fun firstName(firstName: String) = apply { this.firstName = firstName }
        fun lastName(lastName: String) = apply { this.lastName = lastName }
        fun profileMedium(profileMedium: String) = apply { this.profileMedium = profileMedium }
        fun profile(profile: String) = apply { this.profile = profile }
        fun city(city: String) = apply { this.city = city }
        fun state(state: String) = apply { this.state = state }
        fun country(country: String) = apply { this.country = country }
        fun sex(sex: String) = apply { this.sex = sex }
        fun summit(summit: Boolean) = apply { this.summit = summit }
        fun build() = SummaryAthleteGson(id, firstName, lastName, profileMedium, profile, city,
                state, country, sex, summit)
    }
}