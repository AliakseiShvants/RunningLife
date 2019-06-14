package com.shvants.runninglife.model.gson

import com.google.gson.annotations.SerializedName

data class SummaryClubGson(
        @SerializedName("id") val id: Int?,
        @SerializedName("name") val name: String?,
        @SerializedName("profile_medium") val profileMedium: String?
//       @SerializedName("cover_photo") val coverPhoto: String?,
//       @SerializedName("sport_type") val sportType: ActivityType?,
//       @SerializedName("sport_type") val sportType: ActivityType?,

)