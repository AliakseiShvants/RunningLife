package com.shvants.runninglife.data.db.model

import com.shvants.runninglife.data.db.fields.*
import com.shvants.runninglife.utils.Const

open class DetailedActivityDb(
        @dbLong
        @dbPrimaryKey
        override val ID: String = Const.Database.ID,

        @dbString
        override val NAME: String = Const.Database.NAME,

        @dbInt
        override val MOVING_TIME: String = Const.Database.MOVING_TIME,

        @dbString
        override val TYPE: String = Const.Database.TYPE,

        @dbString
        override val START_DATE: String = Const.Database.START_DATE,

        @dbDouble
        override val DISTANCE: String = Const.Database.DISTANCE,

        @dbDouble
        override val AVG_SPEED: String = Const.Database.AVG_SPEED,

        @dbString
        override val MAP: String = Const.Database.MAP,

        @dbInt
        open val ELEVATION: String = Const.Database.ELEVATION,

        @dbString
        open val CALORIES: String = Const.Database.CALORIES,

        @dbString
        open val AVG_HR: String = Const.Database.AVG_HR) :
        SummaryActivityDb(ID, NAME, MOVING_TIME, TYPE, START_DATE, DISTANCE, AVG_SPEED, MAP)
