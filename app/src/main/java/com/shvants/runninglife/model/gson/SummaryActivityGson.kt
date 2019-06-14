package com.shvants.runninglife.model.gson

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.shvants.runninglife.model.base.MetaActivity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SummaryActivityGson(
        @SerializedName("id") val id: Long?,
        @SerializedName("name") val name: String?,
        @SerializedName("moving_time") val movingTime: Int?,
        @SerializedName("type") val type: String?,
        @SerializedName("start_date_local") val startDateLocal: String?,
        @SerializedName("distance") val distance: Float?,
        @SerializedName("average_speed") val averageSpeed: Float?,
        @SerializedName("kudos_count") val kudosCount: Int?,
        @SerializedName("start_latlng") val startLatlng: FloatArray?,
        @SerializedName("end_latlng") val endLatlng: FloatArray?,
        @SerializedName("map") val map: MapGson?) : MetaActivity, Parcelable