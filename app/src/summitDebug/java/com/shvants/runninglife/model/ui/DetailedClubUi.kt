package com.shvants.runninglife.model.ui

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailedClubUi(
        override var id: Int,
        override var name: String,
        override var profileMedium: String,
        var coverPhoto: String,
        var sportType: String,
        var description: String,
        var city: String,
        var state: String,
        var country: String,
        var isPrivate: Boolean,
        var memberCount: Int)
    : SummaryClubUi(id, name, profileMedium), Parcelable