package com.shvants.runninglife.model.gson

import com.google.gson.annotations.SerializedName

//@Parcelize
data class MapGson(
        @SerializedName("polyline") val polyline: String?,
        @SerializedName("summary_polyline") val summaryPolyline: String?) /*: Parcelable*/