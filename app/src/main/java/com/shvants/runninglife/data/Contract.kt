package com.shvants.runninglife.data

import android.util.Log
import com.shvants.runninglife.data.db.model.DbMoveModel
import com.shvants.runninglife.data.db.model.DbUserModel

object Contract {

    private val TAG = Contract::class.simpleName

    fun getTables(): List<Class<*>> {
        Log.d(TAG, "inside getTables method")
        return listOf(DbUserModel::class.java, DbMoveModel::class.java)
    }
}
