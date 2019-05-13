package com.shvants.runninglife.json

data class SummaryAthleteGson(
        val id: Int,
        val firstName: String,
        val lastName: String,
        val profileMedium: String?,
        val profile: String?,
        val city: String?,
        val state: String?,
        val country: String?,
        val sex: String?,
        val summit: Boolean?)