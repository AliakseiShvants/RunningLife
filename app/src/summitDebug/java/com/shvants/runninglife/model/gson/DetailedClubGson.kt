package com.shvants.runninglife.model.gson

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailedClubGson(
        @SerializedName("id") override var id: Int?,
        @SerializedName("name") override var name: String?,
        @SerializedName("profile_medium") override var profileMedium: String?,
        @SerializedName("cover_photo") var coverPhoto: String?,
        @SerializedName("sport_type") var sportType: String?,
        @SerializedName("city") var city: String?

) : SummaryClubGson(id, name, profileMedium), Parcelable