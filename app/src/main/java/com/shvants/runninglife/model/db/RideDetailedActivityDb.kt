package com.shvants.runninglife.model.db

import com.shvants.runninglife.database.fields.*
import com.shvants.runninglife.utils.Const

class RideDetailedActivityDb(
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
        override val ELEVATION: String = Const.Database.ELEVATION,

        @dbString
        override val CALORIES: String = Const.Database.CALORIES,

        @dbString
        override val AVG_HR: String = Const.Database.AVG_HR,

        @dbString
        val AVG_WATTS: String = Const.Database.AVG_WATTS) :
        DetailedActivityDb(ID, NAME, MOVING_TIME, TYPE, START_DATE, DISTANCE, AVG_SPEED, MAP)
