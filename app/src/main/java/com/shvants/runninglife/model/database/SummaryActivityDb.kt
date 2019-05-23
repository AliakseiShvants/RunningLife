package com.shvants.runninglife.model.database

import com.shvants.runninglife.database.Contract
import com.shvants.runninglife.database.fields.*
import com.shvants.runninglife.model.base.MetaActivity
import com.shvants.runninglife.utils.Const

open class SummaryActivityDb(
        @DbLong
        @DbPrimaryKey
        open val ID: String = Contract.ID,

        @DbInt
        open val ATHLETE_ID: String = Contract.ATHLETE_ID,

        @DbString
        open val NAME: String = Const.Database.NAME,

        @DbInt
        open val MOVING_TIME: String = Const.Database.MOVING_TIME,

        @DbString
        open val TYPE: String = Const.Database.TYPE,

        @DbString
        open val START_DATE: String = Const.Database.START_DATE,

        @DbDouble
        open val DISTANCE: String = Const.Database.DISTANCE,

        @DbDouble
        open val AVG_SPEED: String = Const.Database.AVG_SPEED,

        @DbString
        open val MAP: String = Const.Database.MAP) : MetaActivity
