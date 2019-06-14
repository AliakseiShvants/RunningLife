package com.shvants.runninglife.model.gson

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OauthResponse(
        @SerializedName("token_type") val tokenType: String?,
        @SerializedName("expires_at") val expiresAt: Int?,
        @SerializedName("expiresIn") val expiresIn: Int?,
        @SerializedName("refresh_token") val refreshToken: String?,
        @SerializedName("access_token") val accessToken: String?,
        @SerializedName("athlete") val athlete: SummaryAthleteGson?) : Parcelable