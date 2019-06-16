package com.shvants.runninglife.model.ui

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class SummaryAthleteUi(
        override var id: Long,
        open var profile: String,
        open var profileMedium: String,
        open var fullName: String,
        open var location: String) : EntityLongUi(id), Parcelable