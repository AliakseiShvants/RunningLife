package com.shvants.runninglife.model.gson

import com.google.gson.annotations.SerializedName
import com.shvants.runninglife.model.base.MetaActivity

class DetailedActivityGson(
        @SerializedName("id") var id: Long?,
        @SerializedName("name") var name: String?,
        @SerializedName("moving_time") var movingTime: Int?,
        @SerializedName("type") var type: String?,
        @SerializedName("start_date_local") var startDate: String?,
        @SerializedName("distance") var distance: Float?,
        @SerializedName("average_speed") var avgSpeed: Float?,
        @SerializedName("kudos_count") var kudosCount: Int?,
        @SerializedName("start_latlng") var startLatlng: FloatArray?,
        @SerializedName("end_latlng") var endLatlng: FloatArray?,
        @SerializedName("map") var map: MapGson?,
        @SerializedName("total_elevation_gain") var elevation: Float?,
        @SerializedName("calories") var calories: Float?,
        @SerializedName("average_heartrate") var avgHR: Float?) : MetaActivity