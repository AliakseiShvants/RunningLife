package com.shvants.runninglife.model.ui

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class SummaryClubUi(
        override var id: Int,
        open var name: String,
        open var profileMedium: String) : EntityIntUi(id), Parcelable