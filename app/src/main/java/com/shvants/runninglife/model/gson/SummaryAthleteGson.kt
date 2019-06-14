package com.shvants.runninglife.model.gson

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.shvants.runninglife.model.base.MetaAthlete
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SummaryAthleteGson(
        @SerializedName("id") val id: Long?,
        @SerializedName("firstname") val firstname: String?,
        @SerializedName("lastname") val lastname: String?,
        @SerializedName("profile_medium") val profileMedium: String?,
        @SerializedName("profile") val profile: String?,
        @SerializedName("city") val city: String?,
        @SerializedName("state") val state: String?,
        @SerializedName("country") val country: String?,
        @SerializedName("sex") val sex: String?,
        @SerializedName("summit") val summit: Boolean? = false) : MetaAthlete, Parcelable