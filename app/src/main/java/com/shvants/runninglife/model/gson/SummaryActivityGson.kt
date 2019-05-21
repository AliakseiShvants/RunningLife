package com.shvants.runninglife.model.gson

import com.google.gson.annotations.SerializedName
import com.shvants.runninglife.model.base.MetaActivity

data class SummaryActivityGson(
        @SerializedName("id") val id: Long?,
        @SerializedName("name") val name: String?,
        @SerializedName("moving_time") val movingTime: Int?,
        @SerializedName("type") val type: String?,
        @SerializedName("start_date_local") val startDate: String?,
        @SerializedName("distance") val distance: Float? = 0f,
        @SerializedName("average_speed") val avgSpeed: Float? = 0f,
        @SerializedName("map") val map: MapGson?) : MetaActivity