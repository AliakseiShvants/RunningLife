package com.shvants.runninglife.model.ui

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class SummaryActivityUi(
        override var id: Long,
        open var name: String,
        open var movingTime: String,
        open var type: String,
        open var startDate: String,
        open var distance: String,
        open var avgSpeed: String,
        open var kudosCount: Int,
        open var kudos: List<String> = mutableListOf(),
        open var commentCount: Int,
        open var startLatlng: FloatArray,
        open var endLatlng: FloatArray,
        open var map: String
) : EntityLongUi(id), Parcelable