package com.shvants.runninglife.database.model

import com.shvants.runninglife.database.fields.dbLong
import com.shvants.runninglife.database.fields.dbPrimaryKey
import com.shvants.runninglife.database.fields.dbString
import com.shvants.runninglife.utils.Const

class DbUserModel(
        @dbLong
        @dbPrimaryKey
        val ID: String = Const.Database.ID,

        @dbString
        val FULLNAME: String = Const.Database.FULLNAME,

        @dbString
        val PASSWORD: String = Const.Database.PASSWORD,

        @dbString
        val LOCATION: String = Const.Database.LOCATION
) : IDbModel {
    companion object
}