package com.shvants.runninglife.data.db.model

import com.shvants.runninglife.data.base.MetaAthlete
import com.shvants.runninglife.data.db.fields.dbBoolean
import com.shvants.runninglife.data.db.fields.dbLong
import com.shvants.runninglife.data.db.fields.dbPrimaryKey
import com.shvants.runninglife.data.db.fields.dbString
import com.shvants.runninglife.utils.Const

class SummaryAthleteDb(
        @dbLong
        @dbPrimaryKey
        val ID: String = Const.Database.ID,

        @dbString
        val FULLNAME: String = Const.Database.FULLNAME,

        @dbString
        val PROFILE_MEDIUM: String = Const.Database.PROFILE_MEDIUM,

        @dbString
        val PROFILE: String = Const.Database.PROFILE,

        @dbString
        val LOCATION: String = Const.Database.LOCATION,

        @dbString
        val SEX: String = Const.Database.SEX,

        @dbBoolean
        val SUMMIT: String = Const.Database.SUMMIT) : MetaAthlete {
    companion object
}