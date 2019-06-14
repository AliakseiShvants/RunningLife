package com.shvants.runninglife.model.gson

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MapGson(
        @SerializedName("polyline") val polyline: String?,
        @SerializedName("summary_polyline") val summaryPolyline: String?) : Parcelable