package com.shvants.runninglife.model.gson

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
open class SummaryClubGson(
        @SerializedName("id") open var id: Int?,
        @SerializedName("name") open var name: String?,
        @SerializedName("profile_medium") open var profileMedium: String?
//       @SerializedName("cover_photo") open var coverPhoto: String?,
//       @SerializedName("sport_type") open var sportType: ActivityType?,
//       @SerializedName("sport_type") open var sportType: ActivityType?,
) : Parcelable