package com.shvants.runninglife.json

import com.google.gson.annotations.SerializedName

data class SummaryActivityGson(
        @SerializedName("id") val id: Long,
        @SerializedName("name") val name: String,
        @SerializedName("moving_time") val movingTime: Int,
        @SerializedName("type") val type: String,
        @SerializedName("start_date_local") val startDate: String,
        @SerializedName("distance") val distance: Float?,
        @SerializedName("average_speed") val avgSpeed: Float?,
        @SerializedName("map") val map: MapGson?
)