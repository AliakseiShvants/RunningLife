package com.shvants.runninglife.model.gson

import com.google.gson.annotations.SerializedName
import com.shvants.runninglife.model.base.MetaActivity

open class SummaryActivityGson(
        @SerializedName("id") open var id: Long?,
        @SerializedName("name") open var name: String?,
        @SerializedName("moving_time") open var movingTime: Int?,
        @SerializedName("type") open var type: String?,
        @SerializedName("start_date_local") open var startDate: String?,
        @SerializedName("distance") open var distance: Float?,
        @SerializedName("average_speed") open var avgSpeed: Float?,
        @SerializedName("kudos_count") open var kudosCount: Int?,
        @SerializedName("start_latlng") open var startLatlng: FloatArray?,
        @SerializedName("end_latlng") open var endLatlng: FloatArray?,
        @SerializedName("map") open var map: MapGson?) : MetaActivity