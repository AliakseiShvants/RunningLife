package com.shvants.runninglife.data.web.model

import com.google.gson.annotations.SerializedName

data class OauthResponse(
        @SerializedName("token_type") val tokenType: String?,
        @SerializedName("expires_at") val expiresAt: Int?,
        @SerializedName("expires_in") val expiresIn: Int?,
        @SerializedName("refresh_token") val refreshToken: String?,
        @SerializedName("access_token") val accessToken: String?,
        @SerializedName("athlete") val athlete: SummaryAthlete?)