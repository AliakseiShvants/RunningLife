package com.shvants.runninglife.model.database

import com.shvants.runninglife.database.Contract
import com.shvants.runninglife.database.Table
import com.shvants.runninglife.database.fields.DbBoolean
import com.shvants.runninglife.database.fields.DbLong
import com.shvants.runninglife.database.fields.DbPrimaryKey
import com.shvants.runninglife.database.fields.DbString

@Table(name = Contract.AthleteEntry.TABLE_NAME)
data class SummaryAthleteModel(
        @DbLong @DbPrimaryKey val _id: Long,
        @DbString val fullname: String? = "",
        @DbString val profile_medium: String? = "",
        @DbString val profile: String? = "",
        @DbString val location: String? = "",
        @DbString val sex: String? = "",
        @DbBoolean val summit: Int? = 0)