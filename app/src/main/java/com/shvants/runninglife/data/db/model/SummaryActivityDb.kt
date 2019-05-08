package com.shvants.runninglife.data.db.model

import com.shvants.runninglife.data.base.MetaActivity
import com.shvants.runninglife.data.db.fields.*
import com.shvants.runninglife.utils.Const

open class SummaryActivityDb(
        @dbLong
        @dbPrimaryKey
        open val ID: String = Const.Database.ID,

        @dbInt
        open val ATHLETE_ID: String = Const.Database.ATHLETE_ID,

        @dbString
        open val NAME: String = Const.Database.NAME,

        @dbInt
        open val MOVING_TIME: String = Const.Database.MOVING_TIME,

        @dbString
        open val TYPE: String = Const.Database.TYPE,

        @dbString
        open val START_DATE: String = Const.Database.START_DATE,

        @dbDouble
        open val DISTANCE: String = Const.Database.DISTANCE,

        @dbDouble
        open val AVG_SPEED: String = Const.Database.AVG_SPEED,

        @dbString
        open val MAP: String = Const.Database.MAP) : MetaActivity
