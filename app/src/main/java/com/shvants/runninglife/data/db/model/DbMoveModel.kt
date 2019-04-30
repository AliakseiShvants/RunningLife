package com.shvants.runninglife.data.db.model

import com.shvants.runninglife.data.db.fields.*
import com.shvants.runninglife.utils.Const

class DbMoveModel(
        @dbLong
        @dbPrimaryKey
        val ID: String = Const.Database.ID,

        @dbString
        val TITLE: String = Const.Database.TITLE,

        @dbDouble
        val DISTANCE: String = Const.Database.DISTANCE,

        @dbLong
        val TIME: String = Const.Database.TIME,

        @dbInt
        val CALORIES: String = Const.Database.CALORIES,

        @dbInt
        val ELEVATION: String = Const.Database.ELEVATION,

        @dbInt
        val HR: String = Const.Database.HR
) : IDbModel {
    companion object
}
