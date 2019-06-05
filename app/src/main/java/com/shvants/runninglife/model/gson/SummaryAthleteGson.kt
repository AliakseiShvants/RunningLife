package com.shvants.runninglife.model.gson

import com.google.gson.annotations.SerializedName
import com.shvants.runninglife.model.base.MetaAthlete

data class SummaryAthleteGson(
        @SerializedName("id") val id: Long? = 0L,
        @SerializedName("firstname") val firstName: String? = "",
        @SerializedName("lastname") val lastName: String? = "",
        @SerializedName("profile_medium") val profileMedium: String? = "",
        @SerializedName("profile") val profile: String? = "",
        @SerializedName("city") val city: String? = "",
        @SerializedName("state") val state: String? = "",
        @SerializedName("country") val country: String? = "",
        @SerializedName("sex") val sex: String? = "",
        @SerializedName("summit") val summit: Boolean? = false) : MetaAthlete