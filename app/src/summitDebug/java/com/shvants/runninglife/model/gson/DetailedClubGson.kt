package com.shvants.runninglife.model.gson

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailedClubGson(
        @SerializedName("id") var id: Int?,
        @SerializedName("name") var name: String?,
        @SerializedName("profile_medium") var profileMedium: String?,
        @SerializedName("cover_photo") var coverPhoto: String?,
        @SerializedName("sport_type") var sportType: String?,
        @SerializedName("description") var description: String?,
        @SerializedName("city") var city: String?,
        @SerializedName("state") var state: String?,
        @SerializedName("country") var country: String?,
        @SerializedName("private") var isPrivate: Boolean?,
        @SerializedName("member_count") var memberCount: Int?) : Parcelable