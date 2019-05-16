package com.shvants.runninglife.data.web.model

import com.google.gson.annotations.SerializedName

data class SummaryAthlete(
        @SerializedName("id") val id: Int?,
        @SerializedName("firstname") val firstName: String?,
        @SerializedName("lastname") val lastName: String?,
        @SerializedName("profile_medium") val profileMedium: String?,
        @SerializedName("profile") val profile: String?,
        @SerializedName("city") val city: String?,
        @SerializedName("state") val state: String?,
        @SerializedName("country") val country: String?,
        @SerializedName("sex") val sex: String?,
        @SerializedName("summit") val summit: Boolean?)