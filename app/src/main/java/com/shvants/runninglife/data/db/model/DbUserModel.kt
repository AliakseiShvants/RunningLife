package com.shvants.runninglife.data.db.model

import com.shvants.runninglife.data.db.fields.dbLong
import com.shvants.runninglife.data.db.fields.dbPrimaryKey
import com.shvants.runninglife.data.db.fields.dbString
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