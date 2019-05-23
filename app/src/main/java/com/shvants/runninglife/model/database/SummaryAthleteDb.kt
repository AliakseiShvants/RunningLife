package com.shvants.runninglife.model.database

import com.shvants.runninglife.database.Contract
import com.shvants.runninglife.database.fields.DbBoolean
import com.shvants.runninglife.database.fields.DbLong
import com.shvants.runninglife.database.fields.DbPrimaryKey
import com.shvants.runninglife.database.fields.DbString
import com.shvants.runninglife.model.base.MetaAthlete

//@Table(name = Const.Database.ATHLETE_TABLE_NAME)
class SummaryAthleteDb(

        @DbLong
        @DbPrimaryKey
        val _ID: String = Contract.ID,

        @DbString
        val FULLNAME: String = Contract.FULLNAME,

        @DbString
        val PROFILE_MEDIUM: String = Contract.PROFILE_MEDIUM,

        @DbString
        val PROFILE: String = Contract.PROFILE,

        @DbString
        val LOCATION: String = Contract.LOCATION,

        @DbString
        val SEX: String = Contract.SEX,

        @DbBoolean
        val SUMMIT: String = Contract.SUMMIT) : MetaAthlete